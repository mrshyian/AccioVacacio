package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@ToString
public class AlbumFromTripsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

//---------------------------------------------------

    // photos to album
    @OneToMany(mappedBy = "albumFromTripsTable")
    List<PhotosFromTripsTable> photos;

//---------------------------------------------------

    // album to photo
    @OneToOne
    @JoinColumn(name = "photo_id")
    PhotosFromTripsTable photosFromTripsTable;

//---------------------------------------------------

    //albums to user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUserTable myUserTable;

    // user to album
    @OneToOne(mappedBy = "albumFromTripsTable")
    private MyUserTable userTable;

//---------------------------------------------------

    private String country;
    private String city;
    private Date tripDate;
    private String albumName;
    private String aboutAlbum;

    public AlbumFromTripsTable() {
    }

    public AlbumFromTripsTable(String country, String city, Date tripDate, String albumName, String aboutAlbum) {

        this.country = country;
        this.city = city;
        this.tripDate = tripDate;
        this.albumName = albumName;
        this.aboutAlbum = aboutAlbum;
    }
}
