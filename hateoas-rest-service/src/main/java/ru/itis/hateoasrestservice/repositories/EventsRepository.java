package ru.itis.hateoasrestservice.repositories;

import org.aspectj.weaver.ast.Not;
import org.hibernate.id.Assigned;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoasrestservice.models.Event;

import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Long> {

    //  state - Assigned
    //          Not assigned /Published
    //          Canceled
    //          Not observed
    //          Finished

    @RestResource(path = "notAssigned", rel = "notAssigned")
    @Query("from Event event where event.state = 'NotAssigned'")
    Page<Event> findAllNotAssigned(Pageable pageable);

    @RestResource(path = "finished", rel = "finished")
    @Query("from Event event where event.state = 'Finished'")
    Page<Event> findAllFinished(Pageable pageable);

    List<Event> findAllByTitle(String title);
    Event findByEventLineNumber(Integer eventLineNumber);
}