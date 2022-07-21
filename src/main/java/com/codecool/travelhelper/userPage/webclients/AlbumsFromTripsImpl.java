package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.AlbumFromTripsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.NoteTable;
import com.codecool.travelhelper.aws.database.repositories.AlbumsFromTripsRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.aws.imagestore.controllers.AlbumsFromTripImageController;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.codecool.travelhelper.userPage.controllers.AlbumsFromTripsController;
import com.codecool.travelhelper.userPage.models.AlbumsFromTripsModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AlbumsFromTripsImpl {

    @Autowired
    private AlbumsFromTripsRepository albumsFromTripsRepository;

    @Autowired
    private LoginImpl loginImpl;

    @Autowired
    AlbumsFromTripImageController albumsFromTripImageController;

    @Autowired
    private UserRepository userRepository;

    public void setNewAlbum(String albumData) {

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable userFromDB = userRepository.findAllById(userId);

        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject) jsonParser.parse(albumData);
        String albumCountry = commentJsonObject.get("country").getAsString();
        String albumCity = commentJsonObject.get("city").getAsString();
        String albumTripDate = commentJsonObject.get("tripDate").getAsString();
        String albumName = commentJsonObject.get("albumName").getAsString();
        String albumTripDescription = commentJsonObject.get("tripDescription").getAsString();

        AlbumFromTripsTable newAlbum = new AlbumFromTripsTable(albumCountry, albumCity, albumTripDate, albumName, albumTripDescription, userFromDB);

        Optional<AlbumFromTripsTable> response = albumsFromTripsRepository.findAllByMyUserTableIdAndAlbumName(userId, albumName);

        if (response.isPresent()) {
            System.out.println("You already have album with this name!");
        } else {
            albumsFromTripsRepository.save(newAlbum);
        }
    }

    public List<AlbumsFromTripsModel> getAlbums() {
        List<AlbumsFromTripsModel> albums = new ArrayList<>();

        Long userId = loginImpl.getCurrentUserId();

        

        List<Optional<AlbumFromTripsTable>> optionalAlbumsList = albumsFromTripsRepository.findAllByMyUserTableId(userId);
        for (Optional<AlbumFromTripsTable> optional : optionalAlbumsList) {
            if (optional.isPresent()) {
                albums.add(
                        AlbumsFromTripsModel.builder()
                                .albumId(optional.get().getId())
                                .country(optional.get().getCountry())
                                .city(optional.get().getCity())
                                .tripDate(optional.get().getTripDate())
                                .aboutAlbum(optional.get().getAboutAlbum())
                                .albumName(optional.get().getAlbumName())
                                .build()
                );
            }
        }
        return albums;
    }

    public void deleteAlbum(String albumData) {
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject) jsonParser.parse(albumData);
        Long  albumId = Long.parseLong(commentJsonObject.get("albumId").getAsString());

        albumsFromTripImageController.deleteAlbumImage(albumsFromTripsRepository.findAlbumFromTripsTableById(albumId).getAlbumName());
        albumsFromTripsRepository.deleteById(albumId);
    }
}
