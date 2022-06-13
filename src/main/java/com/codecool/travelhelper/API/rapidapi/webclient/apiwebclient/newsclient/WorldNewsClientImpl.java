package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WorldNewsClientImpl extends ApiWebClient implements WorldNewsClient {

    public WorldNewsClientImpl() {
        super(ApiMetaData.NEWSWORLD);
    }

    @Override
    public List<WorldNewsApiModel> getCityNews(String cityName, int amountOfNews) {
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
            put("lang", "en");
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getListOfNewsDetails(response,amountOfNews);
    }

    private List<WorldNewsApiModel> getListOfNewsDetails(JsonObject response, int amountOfNews) {
        List<WorldNewsApiModel> listOfNews = new ArrayList<>();
        for (int i = 0; i < amountOfNews; i++) {
            WorldNewsApiModel singleNews = getSingleNewsDto(response, i);
            listOfNews.add(singleNews);
        }
        return listOfNews;
    }


    private WorldNewsApiModel getSingleNewsDto(JsonObject response, int index) {
        String title = getValueByKeyFromJsonObjectInsideJsonArray("title","articles",response,index);
        String summary = getValueByKeyFromJsonObjectInsideJsonArray("summary","articles",response,index);
        String link = getValueByKeyFromJsonObjectInsideJsonArray("link","articles",response,index);

        return WorldNewsApiModel.builder()
                .summary(summary)
                .title(title)
                .link(link)
                .build();
    }
}
