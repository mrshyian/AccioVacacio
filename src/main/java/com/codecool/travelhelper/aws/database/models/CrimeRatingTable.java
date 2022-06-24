package com.codecool.travelhelper.aws.database.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class CrimeRatingTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private String countryName;

    private int starCount;


    public CrimeRatingTable(String cityName, String countryName, int starCount) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.starCount = starCount;
    }

}
