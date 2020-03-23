package ru.itis.hateoasrestservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.hateoasrestservice.models.Event;
import ru.itis.hateoasrestservice.repositories.EventsRepository;

public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public Event appointment(Long eventId) {
        Event event =  eventsRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
        event.appointment();
        eventsRepository.save(event);
        return event;
    }

}
