package ru.itis.equeue.services;

import ru.itis.equeue.models.Event;

public interface EventsService {
    Event appointment(Long eventId);
}
