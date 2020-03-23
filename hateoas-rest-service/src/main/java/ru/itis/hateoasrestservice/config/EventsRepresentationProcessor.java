package ru.itis.hateoasrestservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoasrestservice.controllers.EventsController;
import ru.itis.hateoasrestservice.models.Event;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Event>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Event> process(EntityModel<Event> model) {
        Event event = model.getContent();
        if (event != null && event.getState().equals("NotAssigned")) {
            model.add(linkTo(methodOn(EventsController.class).appointment(event.getId())).withRel("appointment"));
        }

        if (event != null && event.getState().equals("Assigned")) {
            model.add(links.linkToItemResource(Event.class, event.getId()).withRel("delete"));
        }
        return model;
    }
}