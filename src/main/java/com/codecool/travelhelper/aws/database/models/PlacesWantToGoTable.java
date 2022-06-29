package com.codecool.travelhelper.aws.database.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class PlacesWantToGoTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_want_to_go_id")
    private Long id;

    private String country;
    private String city;

//------------------------------------------------

    // placesWantToGo to user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUserTable myUserTable;

//------------------------------------------------


    public PlacesWantToGoTable(String country, String city, MyUserTable myUserTable) {
        this.country = country;
        this.city = city;
        this.myUserTable = myUserTable;
    }

    @Override
    public String toString() {
        return "PlacesWantToGoTable{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
