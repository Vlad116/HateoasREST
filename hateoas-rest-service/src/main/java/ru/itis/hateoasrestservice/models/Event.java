package ru.itis.hateoasrestservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private Integer eventLineNumber;
    private String title;
    private String eventDescription;
    private LocalDateTime eventStartTime;
    private LocalDateTime recordingIsAvailableUntil;

    private Long averageDuration;
    private Long realDuration;
    // Assigned notAssigned Canceled notObserved Finished
    private String state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shedule_id")
    private Shedule shedule;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public void appointment() {
        if (this.state.equals("NotAssigned")) {
            this.state = "Assigned";
        } else if (this.state.equals("Finished")) {
            throw new IllegalStateException();
        }
    }
}