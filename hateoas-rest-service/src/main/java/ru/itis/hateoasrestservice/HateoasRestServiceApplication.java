package ru.itis.hateoasrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoasrestservice.models.Company;
import ru.itis.hateoasrestservice.models.Event;
import ru.itis.hateoasrestservice.models.Shedule;
import ru.itis.hateoasrestservice.models.User;
import ru.itis.hateoasrestservice.repositories.CompaniesRepository;
import ru.itis.hateoasrestservice.repositories.EventsRepository;
import ru.itis.hateoasrestservice.repositories.ShedulesRepository;
import ru.itis.hateoasrestservice.repositories.UsersRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

@SpringBootApplication
public class HateoasRestServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasRestServiceApplication.class, args);

        UsersRepository usersRepository = context.getBean(UsersRepository.class);
        CompaniesRepository companiesRepository = context.getBean(CompaniesRepository.class);
        EventsRepository eventsRepository = context.getBean(EventsRepository.class);
        ShedulesRepository shedulesRepository = context.getBean(ShedulesRepository.class);

        Company clinic = Company.builder()
                .companyName("Инфекционная больница")
                .companyEmail("infection@gmail.ru")
                .phoneNumber("89871233216")
                .users(Collections.emptyList())
                .shedules(Collections.emptyList())
                .build();

        companiesRepository.save(clinic);

        GregorianCalendar gCalendarStartTime = new GregorianCalendar();
        gCalendarStartTime.set(2020, Calendar.MARCH, 28, 10,0,0);

        GregorianCalendar gCalendarRecordingIsAvailableUntil = new GregorianCalendar();
        gCalendarRecordingIsAvailableUntil.set(2020, Calendar.MARCH, 28, 8,30,0);

        gCalendarStartTime.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
        gCalendarRecordingIsAvailableUntil.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));

        LocalDateTime date = new Timestamp(gCalendarStartTime.getTimeInMillis()).toLocalDateTime();
        int nano = date.getNano();

        System.out.println(nano);
        System.out.println("Время: " + date);

        System.out.println(gCalendarRecordingIsAvailableUntil.toString());
        System.out.println(new Timestamp(gCalendarRecordingIsAvailableUntil.getTimeInMillis()));

        Event firstCOVIDTest = Event.builder()
                .title("Covid test 10:00")
                .eventLineNumber(1)
                .eventDescription("Тест на 'корону'")
                .eventStartTime(date.minusNanos(nano))
                .recordingIsAvailableUntil(new Timestamp(gCalendarRecordingIsAvailableUntil.getTimeInMillis()).toLocalDateTime())
                .averageDuration(15L)
                .realDuration(9L)
                .state("NotAssigned")
                .build();

        gCalendarStartTime.set(2020, Calendar.MARCH, 28, 12, 0, 0);
        gCalendarRecordingIsAvailableUntil.set(2020, Calendar.MARCH, 28, 10,30,0);

        System.out.println("New: " + new Timestamp(gCalendarStartTime.getTimeInMillis()));

        Event secondCOVIDTest = Event.builder()
                .title("Covid test 12:00")
                .eventLineNumber(2)
                .eventDescription("Тест на 'корону', второй по записи")
                .eventStartTime(new Timestamp(gCalendarStartTime.getTimeInMillis()).toLocalDateTime())
                .recordingIsAvailableUntil(new Timestamp(gCalendarRecordingIsAvailableUntil.getTimeInMillis()).toLocalDateTime())
                .averageDuration(15L)
                .realDuration(8L)
                .state("NotAssigned")
                .build();

        gCalendarStartTime.set(2020, Calendar.MARCH, 28, 14, 0, 0);
        gCalendarRecordingIsAvailableUntil.set(2020, Calendar.MARCH, 28, 12,30,0);

        Event thirdCOVIDTest = Event.builder()
                .title("Covid test 14:00")
                .eventLineNumber(3)
                .eventDescription("Запись на тест на 'корону'")
                .eventStartTime(new Timestamp(gCalendarStartTime.getTimeInMillis()).toLocalDateTime())
                .recordingIsAvailableUntil(new Timestamp(gCalendarRecordingIsAvailableUntil.getTimeInMillis()).toLocalDateTime())
                .averageDuration(15L)
                .realDuration(5L)
                .state("NotAssigned")
                .build();

        gCalendarStartTime.set(2020, Calendar.APRIL, 1, 8, 0, 0);
        gCalendarRecordingIsAvailableUntil.set(2020, Calendar.MARCH, 31, 24,0,0);

        Event firstFLUETest = Event.builder()
                .title("Flu shot")
                .eventLineNumber(1)
                .eventDescription("Прививка от гриппа, плановая")
                .eventStartTime(new Timestamp(gCalendarStartTime.getTimeInMillis()).toLocalDateTime())
                .recordingIsAvailableUntil(new Timestamp(gCalendarRecordingIsAvailableUntil.getTimeInMillis()).toLocalDateTime())
                .averageDuration(15L)
                .realDuration(9L)
                .state("NotAssigned")
                .build();

        eventsRepository.saveAll(asList(firstCOVIDTest,
                firstFLUETest,
                secondCOVIDTest,
                thirdCOVIDTest));

        Shedule covidTestShedule = Shedule.builder()
                .description("Запись на сдачу анализа на наличие COVID-19")
                .title("Запись на анализ COVID-19")
                .build();

        Shedule flue = Shedule.builder()
                .description("Плановые прививки от ОРВИ")
                .title("Прививки от ОРВИ")
                .build();

        shedulesRepository.saveAll(asList(
                covidTestShedule, flue
        ));

        clinic.setShedules(asList(covidTestShedule,flue));

        covidTestShedule.setCompany(clinic);
        flue.setCompany(clinic);


        firstCOVIDTest.setShedule(covidTestShedule);
        secondCOVIDTest.setShedule(covidTestShedule);
        thirdCOVIDTest.setShedule(covidTestShedule);

        firstFLUETest.setShedule(flue);

        eventsRepository.saveAll(asList(firstCOVIDTest,secondCOVIDTest,thirdCOVIDTest,firstFLUETest));

        covidTestShedule.setEvents(asList(firstCOVIDTest,
                        secondCOVIDTest,
                        thirdCOVIDTest));

        covidTestShedule.setEvents(Collections.singletonList(firstFLUETest));

        shedulesRepository.saveAll(asList(
                covidTestShedule, flue
        ));

        User daria = User.builder()
                .firstName("Дария")
                .lastName("Шагиева")
                .role("CREATOR")
                .email("daria99@gmail.com")
                .phoneNumber("89541124876")
                .hashPassword("hashpasswordsome")
                .events(Collections.singletonList(firstFLUETest))
                .build();

        User emilka = User.builder()
                .firstName("Эмиль")
                .lastName("Аминов")
                .role("CREATOR")
                .email("emildirector@gmail.com")
                .phoneNumber("89543322876")
                .hashPassword("somehashpassword")
                .events(asList(firstFLUETest,secondCOVIDTest))
                .build();

        usersRepository.saveAll(asList(emilka, daria));

        clinic.setUsers(Collections.singletonList(daria));
        companiesRepository.save(clinic);

        daria.setCompany(clinic);
        usersRepository.saveAll(Collections.singletonList(daria));
    }
}
