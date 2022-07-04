package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.aws.database.models.AlbumFromTripsTable;
import com.codecool.travelhelper.userPage.webclients.AlbumsFromTripsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AlbumsFromTripsController {

    @Autowired
    AlbumsFromTripsImpl albumsFromTrips;

    @PostMapping("/albumsfromtrips")
    public void setNewAlbum(@RequestBody String albumData ) {
        albumsFromTrips.setNewAlbum(albumData);
    }

    @GetMapping("/albumsfromtrips")
    public List<AlbumFromTripsTable> getAlbums(){
        return albumsFromTrips.getAlbums();
    }
}
