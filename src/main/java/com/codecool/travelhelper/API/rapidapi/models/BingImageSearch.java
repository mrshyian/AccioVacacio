package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class BingImageSearch {
    private List<String> imgUrls;
}
