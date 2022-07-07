package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.userPage.models.AlbumsFromTripsModel;
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
    public List<AlbumsFromTripsModel> getAlbums(){
        return albumsFromTrips.getAlbums();
    }

    @PutMapping ("/albumsfromtrips")
    public void deleteAlbum(@RequestBody String albumData ) {
        albumsFromTrips.deleteAlbum(albumData);
    }
}
