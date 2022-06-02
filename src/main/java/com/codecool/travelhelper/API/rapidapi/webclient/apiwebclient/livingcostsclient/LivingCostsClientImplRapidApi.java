package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.livingcostsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.LivingCostsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaDataRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LivingCostsClientImplRapidApi extends ApiWebClientRapidApi  {

    public LivingCostsClientImplRapidApi() {super(ApiMetaDataRapidApi.WEATHER);}

    public LivingCostsDtoRapidApi getCityLivingCosts(String cityName){
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());
        System.out.println(response);

        LivingCostsDtoRapidApi livingCostsDtoRapidApi = toconadole(response);
        System.out.println(livingCostsDtoRapidApi.toString());

        return livingCostsDtoRapidApi;
    }


    public LivingCostsDtoRapidApi toconadole(JsonObject response){
        //nie wiem co tu będzie bo nie mam dostępu do api
        return null;
    }

}
