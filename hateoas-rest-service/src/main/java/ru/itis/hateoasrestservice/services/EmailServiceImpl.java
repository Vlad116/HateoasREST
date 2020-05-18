package ru.itis.hateoasrestservice.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.itis.hateoasrestservice.models.Event;
import ru.itis.hateoasrestservice.models.User;
import ru.itis.hateoasrestservice.repositories.EventsRepository;
import ru.itis.hateoasrestservice.repositories.UsersRepository;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@Service
@PropertySource("classpath:confidential.properties")
public class EmailServiceImpl implements EmailService {

    private final String HEADER = "<h3>Доброго времени суток, firstname!</h3><br>" +
            " <p>Вы зарегистрировались на сервисе электронный очередей EQuery </p>" +
            "<p>Чтобы подтвердить регистрацию, пожалуйста </p>";
    private final String FOOTER = "<p>Если вы не регистрировались, пожалуйста проигнорируйте данное письмо</p>";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public void sendMail(String subject, String text, String email) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mailFrom);
            messageHelper.setTo("vlad-padovan@mail.ru");
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        };

        javaMailSender.send(messagePreparator);
    }

    @RabbitListener(queues = "registration")
    @Override
    public void registration(Long id) {
        User user = usersRepository.findUserById(id).get();
        String appeal = HEADER.replaceFirst("firstName", user.getFirstName());
        String text = appeal + "<a href='http://localhost:3000/confirm/" +
                user.getConfirmString() + "'>" +"пройдите по ссылке" + "</a>" +
                FOOTER;
        sendMail("Подтвреждение регистрации", text, user.getEmail());
    }

    private final String HEADER_NOTIFICATION = "<h3>Доброго времени суток, firstname!</h3><br>" +
            " <p>Вы записались на сервисе электронных очередей EQuery на завтра в ..:..</p>" +
            "<p>Чтобы подтвердить запись, пожалуйста </p>";
    private final String FOOTER_NOTIFICATION = "<p>Если вы не записывались, пожалуйста проигнорируйте данное письмо</p>";

    @RabbitListener(queues = "notification")
    @Override
    public void notification(Long id,Long eventId) {
        User user = usersRepository.findUserById(id).get();
        Event event = eventsRepository.findEventById(eventId).get();
        String appeal = HEADER.replaceFirst("firstName", user.getFirstName());
        String text = appeal + "<a href='http://localhost:3000/profile'>" +"пройдите по ссылке для перехода в профиль и просмотра текущих записей" +
                "</a>" + FOOTER;
        sendMail("Напоминание о записи " + event.getTitle() + "!", text, user.getEmail());
    }
}
