package ru.itis.hateoasrestservice.services;

import ru.itis.hateoasrestservice.models.Event;

public interface EventsService {
    Event appointment(Long eventId);
}
