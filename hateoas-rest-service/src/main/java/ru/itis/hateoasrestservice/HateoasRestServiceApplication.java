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

import java.util.Collections;

import static java.util.Arrays.asList;

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
                .users(asList())
                .shedules(asList())
                .build();

//        companiesRepository.save(clinic);
// Assigned notAssigned Canceled notObserved Finished

        Event firstCOVIDTest = Event.builder()
                .title("Covid test 10:00")
                .eventLineNumber(1)
//                .eventDescription("")
//                .eventStartTime()
//                .recordingIsAvailableUntil()
                .averageDuration(15L)
                .realDuration(9L)
                .state("NotAssigned")
                .build();

        Event secondCOVIDTest = Event.builder()
                .title("Covid test 11:30")
                .eventLineNumber(2)
//                .eventDescription("")
//                .eventStartTime()
//                .recordingIsAvailableUntil()
                .averageDuration(15L)
                .realDuration(8L)
                .state("NotAssigned")
                .build();

        Event thirdCOVIDTest = Event.builder()
                .title("Covid test 12:00")
                .eventLineNumber(3)
//                .eventDescription("")
//                .eventStartTime()
//                .recordingIsAvailableUntil()
                .averageDuration(15L)
                .realDuration(5L)
                .state("NotAssigned")
                .build();

        Event firstFLUETest = Event.builder()
                .title("Что-то с таблицами они проходили")
                .eventLineNumber(1)
//                .eventDescription("")
//                .eventStartTime()
//                .recordingIsAvailableUntil()
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
                .company(clinic)
//                .events(asList(firstCOVIDTest,
//                        secondCOVIDTest,
//                        thirdCOVIDTest))
                .build();

        Shedule flue = Shedule.builder()
                .description("Курс по Базам данных")
                .title("DataLab")
                .company(clinic)
//                .events(asList(firstFLUETest))
                .build();

        shedulesRepository.saveAll(asList(
                covidTestShedule, flue
        ));

        clinic.setShedules(asList(covidTestShedule,flue));

        companiesRepository.save(clinic);

        User daria = User.builder()
                .firstName("Дария")
                .lastName("Шагиева")
                .role("CREATOR")
                .email("daria99@gmail.com")
                .phoneNumber("89541124876")
                .hashPassword("hashpasswordsome")
//                .company()
                .events(Collections.singletonList(firstFLUETest))
                .build();

        User emilka = User.builder()
                .firstName("Эмиль")
                .lastName("Аминов")
                .role("CREATOR")
                .email("emildirector@gmail.com")
                .phoneNumber("89543322876")
                .hashPassword("somehashpassword")
//                .company(clinic)
                .events(asList(firstFLUETest,secondCOVIDTest))
                .build();



//        Сделайте проверку перед save:
//
//        SomeEntity entity = someEntityRepository.findOne(id);
//        if(entity != null){
//            entity.setSomeValue(value);
//            someEntityRepository.save(entity);
//        }
        clinic.setUsers(asList(emilka));
        companiesRepository.save(clinic);

        usersRepository.saveAll(asList(emilka, daria));
    }
}
