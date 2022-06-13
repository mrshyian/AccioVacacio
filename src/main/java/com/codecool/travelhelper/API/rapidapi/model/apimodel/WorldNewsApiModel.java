package com.codecool.travelhelper.API.rapidapi.model.apimodel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorldNewsApiModel {

    String title;
    String summary;
    String link;

    @Override
    public String toString() {
        return "WorldNewsDtoRapidApi{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
