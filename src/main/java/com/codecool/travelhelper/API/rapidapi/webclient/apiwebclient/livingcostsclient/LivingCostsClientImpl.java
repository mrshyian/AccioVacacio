package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.livingcostsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.LivingCostsApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LivingCostsClientImpl extends ApiWebClient {

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

        return getListOfLivingCosts(response);
    }


    public List<LivingCostsApiModel> getListOfLivingCosts(JsonObject response) {
        List<LivingCostsApiModel> listOfLivingCosts = new ArrayList<>();
        List<Integer> listOfIndexes = Arrays.asList(4,7,9,10,11,13,15,17, 0,1,2,3,34,36,40,48);
        for (Integer index: listOfIndexes) {
            LivingCostsApiModel livingCostsDto = getSingleLivingCosts(response, index);
            listOfLivingCosts.add(livingCostsDto);
        }
        return listOfLivingCosts;
    }


    public LivingCostsApiModel getSingleLivingCosts(JsonObject response, int index) {
        String itemName = getValueByKeyFromJsonObjectInsideJsonArray("item_name", "prices", response, index);
        String averagePrice = getValueByKeyFromJsonObjectInsideJsonArray("avg", "prices", response, index);
        String cost = getValueByKeyFromJsonObjectInsideJsonArray("currency_code", "prices", response, index);

        return LivingCostsApiModel.builder()
                .itemName(itemName)
                .averagePrice(averagePrice)
                .cost(cost)
                .build();

    }
}