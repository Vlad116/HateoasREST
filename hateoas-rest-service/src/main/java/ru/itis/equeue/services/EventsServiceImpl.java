package ru.itis.equeue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.equeue.models.Event;
import ru.itis.equeue.repositories.EventsRepository;

@Service
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
