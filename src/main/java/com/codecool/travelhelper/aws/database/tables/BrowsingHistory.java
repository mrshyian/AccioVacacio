package com.codecool.travelhelper.aws.database.tables;


import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@ToString
public class BrowsingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;
    private String country;
    private String city;
    private DateTime dateTime;

    public BrowsingHistory() {
    }

    public BrowsingHistory(UUID userId, String country, String city, DateTime dateTime) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.dateTime = dateTime;
    }
}
