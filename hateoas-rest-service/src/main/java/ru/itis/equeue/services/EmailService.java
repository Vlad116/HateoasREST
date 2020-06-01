package ru.itis.equeue.services;

public interface EmailService {
    void sendMail(String subject, String text, String email);
    void registration(Long id);
    void notification(Long id, Long eventId);
    //    void registration(User user); - не работает(
}
