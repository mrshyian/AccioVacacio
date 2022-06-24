package com.codecool.travelhelper.API.simple.models;


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
