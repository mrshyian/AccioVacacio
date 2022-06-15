package com.codecool.travelhelper.aws.database.tables.search_city_tables;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CrimeRatingTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    private Long searchingPlaceId;
    private String cityName;
    private String countryName;

    private int starCount;

    public CrimeRatingTable() {
    }

    public CrimeRatingTable(String cityName, String countryName, int starCount) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.starCount = starCount;
    }


    public int getStarCount() {
        return starCount;
    }
}
