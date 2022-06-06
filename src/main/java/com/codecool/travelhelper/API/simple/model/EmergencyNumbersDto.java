package com.codecool.travelhelper.API.simple.model;


import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmergencyNumbersDto {
    private String ambulance;
    private String police;
    private String fireGuard;
    private String dispatch;
}
