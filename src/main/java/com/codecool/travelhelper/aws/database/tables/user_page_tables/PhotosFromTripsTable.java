package com.codecool.travelhelper.aws.database.tables.user_page_tables;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
public class PhotosFromTripsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    AlbumsFromsTripsTable albumsFromsTripsTable;

    @OneToOne(mappedBy = "photosFromTripsTable")
    AlbumsFromsTripsTable album;

    private String linkToPhoto;

}
