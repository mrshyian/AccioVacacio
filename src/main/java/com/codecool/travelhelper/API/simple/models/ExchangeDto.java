package com.codecool.travelhelper.API.simple.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@Builder
public class ExchangeDto {
    private String howMuchToConvert;
    private String howMuchAfterConvert;
    private String countryFrom;
    private String countryTo;

}
