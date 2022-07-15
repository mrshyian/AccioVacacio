package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorldNewsApiModel {

    private String title;
    private String summary;
    private String link;

    @Override
    public String toString() {
        return "WorldNewsDtoRapidApi{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
