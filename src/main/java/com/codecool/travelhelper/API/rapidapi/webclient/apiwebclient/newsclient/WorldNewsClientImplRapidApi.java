package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaDataRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WorldNewsClientImplRapidApi extends ApiWebClientRapidApi implements WorldNewsClientRapidApi {

    public WorldNewsClientImplRapidApi() {
        super(ApiMetaDataRapidApi.NEWSWORLD);
    }

    @Override
    public WorldNewsDtoRapidApi getCityNews(String cityName) {
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());
        System.out.println(response);

        WorldNewsDtoRapidApi newsDto = getnewsDto(response);
        System.out.println(newsDto.toString());

        return newsDto;
    }

    private WorldNewsDtoRapidApi getnewsDto(JsonObject response) {
        String title = getValueByKeyFromJsonObjectInsideJsonArray("title","articles",response,17);
        String summary = getValueByKeyFromJsonObjectInsideJsonArray("summary","articles",response,17);
        System.out.println(title);
        System.out.println(summary);

        WorldNewsDtoRapidApi worldNews = WorldNewsDtoRapidApi.builder()
                .summary(summary)
                .title(title)
                .build();
        return worldNews;
    }


}
