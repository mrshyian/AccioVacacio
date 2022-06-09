package com.codecool.travelhelper.aws.database.tables;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@ToString
public class BrowsingHostory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;
    private String country;
    private String city;
    private Date dateForSearch;

    public BrowsingHostory() {
    }

    public BrowsingHostory(UUID userId, String country, String city, Date dateForSearch) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.dateForSearch = dateForSearch;
    }
}
