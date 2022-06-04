package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.livingcostsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.LivingCostsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaDataRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LivingCostsClientImplRapidApi extends ApiWebClientRapidApi {

    public LivingCostsClientImplRapidApi() {
        super(ApiMetaDataRapidApi.LIVING_COSTS);
    }

    public List<LivingCostsDtoRapidApi> getCityLivingCosts(String cityName, String countryName) {
        Map<String, Object> parameters = new HashMap<>() {{
            put("city_name", cityName);
            put("country_name", countryName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());
        System.out.println(response);

        List<LivingCostsDtoRapidApi> livingCostsDto = getListOfLivingCosts(response);
        System.out.println(livingCostsDto.toString());

        return livingCostsDto;
    }


    public List<LivingCostsDtoRapidApi> getListOfLivingCosts(JsonObject response) {
        List<LivingCostsDtoRapidApi> listOfLivingCosts = new ArrayList<>();
        List<Integer> listOfIndexes = Arrays.asList(4,7,9,10,11,13,15,17, 0,1,2,3,34,36,40,48);
        for (Integer index: listOfIndexes) {
            LivingCostsDtoRapidApi livingCostsDto = getSingleLivingCosts(response, index);
            listOfLivingCosts.add(livingCostsDto);
        }
        return listOfLivingCosts;
    }


    public LivingCostsDtoRapidApi getSingleLivingCosts(JsonObject response, int index) {
        String itemName = getValueByKeyFromJsonObjectInsideJsonArray("item_name", "prices", response, index);
        String averagePrice = getValueByKeyFromJsonObjectInsideJsonArray("avg", "prices", response, index);
        String cost = getValueByKeyFromJsonObjectInsideJsonArray("currency_code", "prices", response, index);

        LivingCostsDtoRapidApi LivingCostDto = LivingCostsDtoRapidApi.builder()
                .itemName(itemName)
                .averagePrice(averagePrice)
                .cost(cost)
                .build();
        return LivingCostDto;

    }


}
