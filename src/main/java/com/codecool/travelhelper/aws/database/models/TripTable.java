package com.codecool.travelhelper.aws.database.models;

import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;


@Entity
@Getter
@ToString
@NoArgsConstructor
public class TripTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long Id;

    private String country;
    private String city;
    private String startDate;
    private String finishDate;
    private float meals;
    private float booking;
    private float souvenirs;
    private float transport;
    private float entertainments;
    private String currency;


//---------------------------------------------

    // trips to user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUserTable myUserTable;

    // user to trip
    @OneToOne(mappedBy = "tripTable")
    private MyUserTable userTable;

//---------------------------------------------


    public TripTable(String country, String city, String startDate, String finishDate, float meals, float booking, float souvenirs, float transport, float entertainments, String currency) {
        this.country = country;
        this.city = city;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.meals = meals;
        this.booking = booking;
        this.souvenirs = souvenirs;
        this.transport = transport;
        this.entertainments = entertainments;
        this.currency = currency;
    }
}
