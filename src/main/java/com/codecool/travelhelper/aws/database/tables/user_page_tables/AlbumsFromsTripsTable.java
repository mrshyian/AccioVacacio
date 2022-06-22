package com.codecool.travelhelper.aws.database.tables.user_page_tables;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@ToString
public class AlbumsFromsTripsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    @OneToMany(mappedBy = "albumsFromsTripsTable")
    List<PhotosFromTripsTable> photos;

    @OneToOne
    @JoinColumn(name = "photo_id")
    PhotosFromTripsTable photosFromTripsTable;


    private String country;
    private String city;
    private Date tripDate;
    private String albumName;
    private String aboutAlbum;

    public AlbumsFromsTripsTable() {
    }

    public AlbumsFromsTripsTable( String country, String city, Date tripDate, String albumName, String aboutAlbum) {

        this.country = country;
        this.city = city;
        this.tripDate = tripDate;
        this.albumName = albumName;
        this.aboutAlbum = aboutAlbum;
    }
}
