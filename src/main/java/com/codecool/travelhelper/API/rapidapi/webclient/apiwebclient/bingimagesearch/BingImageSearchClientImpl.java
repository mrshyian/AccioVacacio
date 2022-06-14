package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.bingimagesearch;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BingImageSearch;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BingImageSearchClientImpl extends ApiWebClient implements BingImageSearchClient {

    public BingImageSearchClientImpl() {
        super(ApiMetaData.BING_IMAGE_SEARCH);
    }

    @Override
    public BingImageSearch getImagesUrl(String searchText, int howMuchImages) {
        Map<String, Object> parameters = new HashMap<>() {{
            put("q", searchText);
            put("offset", 1);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        BingImageSearch bingImageSearch = getBingImageSearchDto(response, howMuchImages);

        return bingImageSearch;
    }

    public BingImageSearch getBingImageSearchDto(JsonObject response, int howMuchImages) {
        List<String> images = new ArrayList<>();
        String contentUrl;

        int responseSize = response.getAsJsonArray("value").size();
        System.out.println(responseSize);
        int amountOfImages = Math.min(howMuchImages, responseSize);

        for (int i = 0; i <amountOfImages ; i++) {
            contentUrl = getValueByKeyFromJsonObjectInsideJsonArray("contentUrl", "value", response,i);
            images.add(contentUrl);
        }

        BingImageSearch bingImageSearch = BingImageSearch.builder()
                .imgUrls(images)
                .build();

        return bingImageSearch;
    }

}
