package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class AlbumFromTripsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

//---------------------------------------------------

    // photos to album
    @OneToMany(mappedBy = "albumFromTripsTable", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotosFromTripsTable> photos;

//---------------------------------------------------

    //albums to user
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private MyUserTable myUserTable;

//---------------------------------------------------

    private String country;
    private String city;
    private String tripDate;
    private String albumName;
    private String aboutAlbum;

    public AlbumFromTripsTable() {
    }

    public AlbumFromTripsTable(String country, String city, String tripDate, String albumName, String aboutAlbum, MyUserTable myUserTable) {

        this.country = country;
        this.city = city;
        this.tripDate = tripDate;
        this.albumName = albumName;
        this.aboutAlbum = aboutAlbum;
        this.myUserTable=myUserTable;
    }

    @Override
    public String toString() {
        return "AlbumFromTripsTable{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", tripDate='" + tripDate + '\'' +
                ", albumName='" + albumName + '\'' +
                ", aboutAlbum='" + aboutAlbum + '\'' +
                '}';
    }
}
