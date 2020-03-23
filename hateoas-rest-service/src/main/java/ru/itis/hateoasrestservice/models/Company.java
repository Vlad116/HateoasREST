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
public class Company {
    @Id
    @GeneratedValue
    private  Long id;

    private String companyName;
    private String companyEmail;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Shedule> shedules;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<User> users;


}
