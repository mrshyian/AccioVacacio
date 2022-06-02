package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaDataRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WorldNewsClientImplRapidApi extends ApiWebClientRapidApi implements WorldNewsClientRapidApi {

    public WorldNewsClientImplRapidApi() {
        super(ApiMetaDataRapidApi.NEWSWORLD);
    }

    @Override
    public List<WorldNewsDtoRapidApi> getCityNews(String cityName, int amountOfNews) {
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());
        System.out.println(response);

        List<WorldNewsDtoRapidApi> listOfNewsDetails = getListOfNewsDetails(response,amountOfNews);

        return listOfNewsDetails;
    }

    private List<WorldNewsDtoRapidApi> getListOfNewsDetails(JsonObject response, int amountOfNews) {
        List<WorldNewsDtoRapidApi> listOfNews = new ArrayList<>();
        for (int i = 0; i < amountOfNews; i++) {
            WorldNewsDtoRapidApi singleNews = getSingleNewsDto(response, i);
            listOfNews.add(singleNews);
        }
        return listOfNews;
    }


    private WorldNewsDtoRapidApi getSingleNewsDto(JsonObject response, int index) {
        String title = getValueByKeyFromJsonObjectInsideJsonArray("title","articles",response,index);
        String summary = getValueByKeyFromJsonObjectInsideJsonArray("summary","articles",response,index);
        String link = getValueByKeyFromJsonObjectInsideJsonArray("link","articles",response,index);

        WorldNewsDtoRapidApi worldNews = WorldNewsDtoRapidApi.builder()
                .summary(summary)
                .title(title)
                .link(link)
                .build();
        return worldNews;
    }

}
