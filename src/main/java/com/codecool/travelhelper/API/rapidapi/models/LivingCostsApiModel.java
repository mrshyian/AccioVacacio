package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LivingCostsApiModel {
    private String itemName;
    private String averagePrice;
    private String cost;

    @Override
    public String toString() {
        return "LivingCostsDtoRapidApi{" +
                "itemName='" + itemName + '\'' +
                ", averagePrice='" + averagePrice + '\'' +
                ", currency='" + cost + '\'' +
                '}';
    }
}
