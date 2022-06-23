package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class LivingCostsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private String countryName;
    private String itemName;
    private String averagePrice;
    private String currency;


    public LivingCostsTable(String cityName, String countryName, String itemName, String averagePrice, String currency) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.itemName = itemName;
        this.averagePrice = averagePrice;
        this.currency = currency;
    }
}
