package com.codecool.travelhelper.aws.database.models;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@ToString
public class PlacesWantToGoTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String city;

//------------------------------------------------

    // places to user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUserTable myUserTable;

    // user to place
    @OneToOne(mappedBy = "placesWantToGo")
    private MyUserTable userTable;

//------------------------------------------------
}
