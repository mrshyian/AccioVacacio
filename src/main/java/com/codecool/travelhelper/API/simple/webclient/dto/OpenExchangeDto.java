package com.codecool.travelhelper.API.simple.webclient.dto;

import com.codecool.travelhelper.API.simple.models.ExchangeDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OpenExchangeDto {
    private double conversion_rate;
}
