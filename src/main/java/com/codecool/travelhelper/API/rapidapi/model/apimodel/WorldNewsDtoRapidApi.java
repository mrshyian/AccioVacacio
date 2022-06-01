package com.codecool.travelhelper.API.rapidapi.model.apimodel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorldNewsDtoRapidApi {

    String title;
    String summary;

    @Override
    public String toString() {
        return "WorldNewsDtoRapidApi{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }


}
