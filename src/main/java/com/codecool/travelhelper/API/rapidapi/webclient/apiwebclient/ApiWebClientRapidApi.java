package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public abstract class ApiWebClientRapidApi {

    protected ApiMetaDataRapidApi apiMetaData;
    private Map<String, Object> parameters = new HashMap<>();

    public JsonObject getApiResponse(String url, Map<String, String> headersData, Map<String, ?> parameters){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        // set `Content-Type` and `Accept` headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        for (var entry: headersData.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }

        HttpEntity request = new HttpEntity(headers);

        String linkWithParameters = getLinkWithParameters(url, parameters);

        ResponseEntity<String> response = restTemplate.exchange(
                linkWithParameters,
                HttpMethod.GET,
                request,
                String.class
        );
        JsonObject responseJson = new JsonParser().parse(response.getBody()).getAsJsonObject();

        return responseJson;
    }


    /**
     * Should be used for case "base":
     * response = {"base":"stations",
     *              "coord":{"lon":-0.1257,"lat":51.5085},
     *              "weather":[
     *                          {"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}
     *                        ]
     *             }
     *
     * @param key
     * @param jsonObject
     * @return String with value for "base", where "base" is element of the first level of nesting.
     */
    public String getValueByKeyFromJsonObject (String key, JsonObject jsonObject) {
        return jsonObject.get(key).getAsString();
    }


    /**
     * Should be used for case "coord":
     *      * response = {"base":"stations",
     *      *              "coord":{"lon":-0.1257,"lat":51.5085},
     *      *              "weather":[
     *      *                          {"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}
     *      *                        ]
     *      *             }
     *      *
     * @param keyForValue
     * @param keyForJsonObject
     * @param jsonObject
     * @return String with value for "coord", where "coord" is element of the second level of nesting - json object inside json object.
     */
    public String getValueByKeyFromJsonObjectInsideJsonObject (String keyForValue, String keyForJsonObject, JsonObject jsonObject){
        JsonObject innerJsonObject = jsonObject.getAsJsonObject(keyForJsonObject);
        JsonElement value = innerJsonObject.get(keyForValue);
        return value.getAsString();
    }


    /**
     * * Should be used for case "weather":
     *      *      * response = {"base":"stations",
     *      *      *              "coord":{"lon":-0.1257,"lat":51.5085},
     *      *      *              "weather":[
     *      *      *                          {"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}
     *      *      *                        ]
     *      *      *             }
     *      *      *
     * @param keyForValue
     * @param keyForJsonArray
     * @param jsonObject
     * @return String with value for "weather", where "coord" is element of the third level of nesting - json object inside json array.
     */
    public String getValueByKeyFromJsonObjectInsideJsonArray (String keyForValue, String keyForJsonArray, JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.getAsJsonArray(keyForJsonArray);
        JsonObject innerJsonObject = jsonArray.get(0).getAsJsonObject();
        JsonElement value = innerJsonObject.get(keyForValue);
        return value.getAsString();
    }

    public ApiWebClientRapidApi() {}

    public ApiWebClientRapidApi(ApiMetaDataRapidApi apiMetaData) {
        this.apiMetaData = apiMetaData;
    }

    public String getUrl() { return apiMetaData.getUrl(); }

    public void setUrl(String url) { this.apiMetaData.setUrl(url); }

    public Map<String, String> getHeadersData() { return apiMetaData.getHeadersData(); }

    public void setHeadersData(Map<String, String> headersData) { this.apiMetaData.setHeadersData(headersData); }

    public Map<String, Object> getParameters() { return parameters; }

    public void setParameters(Map<String, Object> parameters) { this.parameters = parameters; }

    public String getLinkWithParameters(String url, Map<String, ?> parameters){
        return url + "?" + linkTail(parameters);
    }

    public void addHeadersData(String key, String value) { this.getHeadersData().put(key, value); }

    public void addHeadersData(Map<String, String> headersData){ this.getHeadersData().putAll(headersData);}

    public void addParameters(String key, Object value){ parameters.put(key, value); }

    public void addParameters(Map<String, Object> parameters) { this.parameters.putAll(parameters);}

    private String linkTail(Map<String, ?> parameters){
        String tail = "";

        for (var entry: parameters.entrySet()) {
            tail += entry + "&";
        }

        return tail.substring(0,tail.length()-1);
    }
}
