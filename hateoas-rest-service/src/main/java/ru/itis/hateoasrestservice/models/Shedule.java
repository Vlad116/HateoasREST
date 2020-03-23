package ru.itis.hateoasrestservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shedule {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shedule")
    private List<Event> events;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

}
