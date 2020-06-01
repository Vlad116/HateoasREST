package ru.itis.equeue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.equeue.services.EventsService;

@RepositoryRestController
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = "/events/{event-id}/appointment", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> appointment(@PathVariable("event-id") Long eventId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        eventsService.appointment(eventId)
                )
        );
    }

    @RequestMapping(value = "/events/{event-id}/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("event-id") Long eventId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        eventsService.appointment(eventId)
                )
        );
    }

}