package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Setter
public class EmergencyNumbersTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private String countryName;
    private String ambulance;
    private String police;
    private String fireGuard;
    private String dispatch;


    public EmergencyNumbersTable(String cityName, String countryName, String ambulance, String police, String fireGuard, String dispatch) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.ambulance = ambulance;
        this.police = police;
        this.fireGuard = fireGuard;
        this.dispatch = dispatch;
    }
}
