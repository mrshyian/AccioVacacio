package com.codecool.travelhelper.aws.database.models;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class PhotosFromTripsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;
    private String linkToPhoto;

//---------------------------------------------------

    // photos to album
    @ManyToOne
    @JoinColumn(name = "album_id")
    AlbumFromTripsTable albumFromTripsTable;
//---------------------------------------------------

    // album to photo
    @OneToOne(mappedBy = "photosFromTripsTable")
    AlbumFromTripsTable album;
//---------------------------------------------------



}
