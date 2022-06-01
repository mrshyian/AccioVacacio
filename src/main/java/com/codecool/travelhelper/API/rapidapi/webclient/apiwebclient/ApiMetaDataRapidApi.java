package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient;

import java.util.HashMap;
import java.util.Map;

public enum ApiMetaDataRapidApi {
    WEATHER ("https://community-open-weather-map.p.rapidapi.com/weather", new HashMap<>(){{
        put("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com");
        put("X-RapidAPI-Key", "af26e6ba1cmshe3e8f978bdede11p150bcajsna014b084fe98");
    }}),
    NEXT_API("http://", new HashMap<>(){{
        put("first-key", "smth");
        put("second-key", "123");
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
