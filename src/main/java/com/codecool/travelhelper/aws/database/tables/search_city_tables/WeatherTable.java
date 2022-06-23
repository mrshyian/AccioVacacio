package com.codecool.travelhelper.aws.database.tables.search_city_tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@Setter
public class WeatherTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long searchingPlaceId;
    private String cityName;
    private String countryName;

    private String description;
    private int temperature;
    private int feelsLike;
    private int pressure;
    private int humidity;
    private float wingSpeed;

    public WeatherTable() {
    }

    public WeatherTable(String cityName, String countryName, String description, int temperature, int feelsLike, int pressure, int humidity, float wingSpeed) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wingSpeed = wingSpeed;
    }
}
