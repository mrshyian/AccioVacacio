package com.codecool.travelhelper.API.rapidapi.apimodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LivingCostsApiModel {
    String itemName;
    String averagePrice;
    String cost;

    @Override
    public String toString() {
        return "LivingCostsDtoRapidApi{" +
                "itemName='" + itemName + '\'' +
                ", averagePrice='" + averagePrice + '\'' +
                ", currency='" + cost + '\'' +
                '}';
    }
}
