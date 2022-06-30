package com.codecool.travelhelper.API.rapidapi.webclients.livingcostsclient;

import com.codecool.travelhelper.API.rapidapi.models.LivingCostsApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.codecool.travelhelper.aws.database.models.LivingCostsTable;
import com.codecool.travelhelper.aws.database.repositories.jdbc.LivingCostsRepositoryImpl;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LivingCostsClientImpl extends ApiWebClient {

    @Autowired
    LivingCostsRepositoryImpl livingCostsRepositoryImpl;

    public LivingCostsClientImpl() {
        super(ApiMetaData.LIVING_COSTS);
    }

    public List<LivingCostsApiModel> getCityLivingCosts(String cityName, String countryName) {
        Map<String, Object> parameters = new HashMap<>() {{
            put("city_name", cityName);
            put("country_name", countryName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getListOfLivingCosts(response, cityName, countryName);
    }


    public List<LivingCostsApiModel> getListOfLivingCosts(JsonObject response, String cityName, String countryName) {
        List<LivingCostsApiModel> listOfLivingCosts = new ArrayList<>();
        List<Integer> listOfIndexes = Arrays.asList(4,7,9,10,11,13,15,17, 0,1,2,3,34,36,40,48);
        for (Integer index: listOfIndexes) {
            LivingCostsApiModel livingCostsDto = getSingleLivingCosts(response, index, cityName, countryName);
            listOfLivingCosts.add(livingCostsDto);
        }
        return listOfLivingCosts;
    }


    public LivingCostsApiModel getSingleLivingCosts(JsonObject response, int index, String cityName, String countryName) {

        String itemName = getValueByKeyFromJsonObjectInsideJsonArray("item_name", "prices", response, index);
        String averagePrice = getValueByKeyFromJsonObjectInsideJsonArray("avg", "prices", response, index);
        String cost = getValueByKeyFromJsonObjectInsideJsonArray("currency_code", "prices", response, index);

//        Long searchingPlaceId = 1L;
        //----------------------------saving emergency numbers to database----------------------------
        livingCostsRepositoryImpl.setLivingCostsDataByCityAndCountryNameAndItemName(
                new LivingCostsTable(
                        cityName,
                        countryName,
                        itemName,
                        averagePrice,
                        cost
                )
        );
        //--------------------------------------------------------------------------------------------

        return LivingCostsApiModel.builder()
                .itemName(itemName)
                .averagePrice(averagePrice)
                .cost(cost)
                .build();

    }
}
