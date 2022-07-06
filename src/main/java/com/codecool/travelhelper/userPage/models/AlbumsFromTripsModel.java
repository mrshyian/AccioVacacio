package com.codecool.travelhelper.userPage.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AlbumsFromTripsModel {
    private Long albumId;
    private String country;
    private String city;
    private String tripDate;
    private String albumName;
    private String aboutAlbum;
}
