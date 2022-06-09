package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient;

import java.util.HashMap;
import java.util.Map;

public enum ApiMetaDataRapidApi {
    WEATHER ("https://community-open-weather-map.p.rapidapi.com/weather", new HashMap<>(){{
        put("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com");
        put("X-RapidAPI-Key", "af26e6ba1cmshe3e8f978bdede11p150bcajsna014b084fe98");
    }}),
    TRUE_WAY_PLACES("https://trueway-places.p.rapidapi.com/FindPlacesNearby", new HashMap<>(){{
        put("X-RapidAPI-Host", "trueway-places.p.rapidapi.com");
        put("X-RapidAPI-Key", "af26e6ba1cmshe3e8f978bdede11p150bcajsna014b084fe98");
    }}),

    //todo: use it very carefully! Price is 200/month for free + US$0.001 each other
    GOOGLE_AUTOCOMPLETE_PLUS("https://google-maps-autocomplete-plus.p.rapidapi.com/autocomplete",  new HashMap<>(){{
        put("X-RapidAPI-Host", "google-maps-autocomplete-plus.p.rapidapi.com");
        put("X-RapidAPI-Key", "af26e6ba1cmshe3e8f978bdede11p150bcajsna014b084fe98");
    }})

    ;



    private String url;
    private Map<String, String> headersData;

    ApiMetaDataRapidApi(String url, Map<String, String> headersData) {
        this.url = url;
        this.headersData = headersData;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Map<String, String> getHeadersData() { return headersData; }

    public void setHeadersData(Map<String, String> headersData) { this.headersData = headersData; }
}
