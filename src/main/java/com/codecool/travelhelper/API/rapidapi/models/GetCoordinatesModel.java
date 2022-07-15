package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GetCoordinatesModel {
    private float lat;
    private float lon;
}
