package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.WorldNewsRepository;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.WeatherTable;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.WorldNewsTable;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WorldNewsClientImpl extends ApiWebClient implements WorldNewsClient {

    @Autowired
    WorldNewsRepository worldNewsRepository;

    public WorldNewsClientImpl() {
        super(ApiMetaData.NEWSWORLD);
    }

    @Override
    public List<WorldNewsApiModel> getCityNews(String cityName, int amountOfNews, String countryName) {
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
            put("lang", "en");
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getListOfNewsDetails(response, amountOfNews, cityName, countryName);
    }

    private List<WorldNewsApiModel> getListOfNewsDetails(JsonObject response, int amountOfNews, String cityName, String countryName) {
        List<WorldNewsApiModel> listOfNews = new ArrayList<>();
        for (int i = 0; i < amountOfNews; i++) {
            WorldNewsApiModel singleNews = getSingleNewsDto(response, i, cityName, countryName);
            listOfNews.add(singleNews);
        }
        return listOfNews;
    }


    private WorldNewsApiModel getSingleNewsDto(JsonObject response, int index, String cityName, String countryName) {
        String title = getValueByKeyFromJsonObjectInsideJsonArray("title","articles",response,index);
        String summary = getValueByKeyFromJsonObjectInsideJsonArray("summary","articles",response,index);
        String link = getValueByKeyFromJsonObjectInsideJsonArray("link","articles",response,index);

        // Long searchingPlaceId = 1L;
        //----------------------------saving emergency numbers to database----------------------------
//        worldNewsRepository.save(
//                new WorldNewsTable(
//                        cityName,
//                        countryName,
//                        title,
//                        summary,
//                        link
//                )
//        );
        //--------------------------------------------------------------------------------------------

        return WorldNewsApiModel.builder()
                .summary(summary)
                .title(title)
                .link(link)
                .build();
    }
}
