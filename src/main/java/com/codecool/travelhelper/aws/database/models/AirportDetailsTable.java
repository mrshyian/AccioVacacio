package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
public class AirportDetailsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long searchingPlaceId;
    private String cityName;
    private String countryName;

    private String airportName;
    private String airportLocation;
    private String airportStreetNumber;
    private String airportStreetName;
    private String airportState;
    private String airportCity;
    private String airportPhoneNumber;
    private String airportWebsite;

    public AirportDetailsTable() {
    }

    public AirportDetailsTable(String cityName, String countryName, String airportName, String airportLocation, String airportStreetNumber, String airportStreetName, String airportState, String airportCity, String airportPhoneNumber, String airportWebsite) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.airportName = airportName;
        this.airportLocation = airportLocation;
        this.airportStreetNumber = airportStreetNumber;
        this.airportStreetName = airportStreetName;
        this.airportState = airportState;
        this.airportCity = airportCity;
        this.airportPhoneNumber = airportPhoneNumber;
        this.airportWebsite = airportWebsite;
    }
}

