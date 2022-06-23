package com.codecool.travelhelper.aws.database.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
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


    public PhotosFromTripsTable(String linkToPhoto) {
        this.linkToPhoto = linkToPhoto;
    }

}
