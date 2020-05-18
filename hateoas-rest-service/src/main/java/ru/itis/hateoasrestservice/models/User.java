package ru.itis.hateoasrestservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "service_users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String phoneNumber;
    private String hashPassword;
    private String role;
    private Boolean isUserNonLocked;
    private Boolean isEmailConfirmed;
    @JsonIgnore
    private String confirmString;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Event> events;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

//    @Enumerated(value = EnumType.STRING)
//    private Role role;

}