package ru.itis.companies.models;

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
    private String inn;
    private String companyname;
    private String companyemail;
    private String phonenumber;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
//    private List<Shedule> shedules;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
//    private List<User> users;


}
