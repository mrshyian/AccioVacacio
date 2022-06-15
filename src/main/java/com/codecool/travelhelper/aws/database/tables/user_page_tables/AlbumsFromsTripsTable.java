package com.codecool.travelhelper.aws.database.tables.user_page_tables;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@ToString
public class AlbumsFromsTripsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;
    private String country;
    private String city;
    private Date tripDate;
    private String albumName;
    private String aboutAlbum;

    public AlbumsFromsTripsTable() {
    }

    public AlbumsFromsTripsTable(UUID userId, String country, String city, Date tripDate, String albumName, String aboutAlbum) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.tripDate = tripDate;
        this.albumName = albumName;
        this.aboutAlbum = aboutAlbum;
    }
}
