package com.codecool.travelhelper.API.rapidapi.webclients.bingimagesearch;

import com.codecool.travelhelper.API.rapidapi.models.BingImageSearch;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BingImageSearchClientImpl extends ApiWebClient implements BingImageSearchClient {

    private String EMPTY_IMAGE_URL = "https://memegenerator.net/img/instances/75128323/i-think-i-saw-nothing.jpg";

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
        int amountOfImages = Math.min(howMuchImages, responseSize);

        for (int i = 0; i <amountOfImages ; i++) {
            contentUrl = getValueByKeyFromJsonObjectInsideJsonArray("contentUrl", "value", response,i);

            if (contentUrl.contains(".jpg")){
                String imageName = contentUrl.split(".jpg")[0];
                contentUrl = imageName + ".jpg";
            } else if (contentUrl.contains(".jpeg")){
                String imageName = contentUrl.split(".jpeg")[0];
                contentUrl = imageName + ".jpeg";
            } else {
                contentUrl = EMPTY_IMAGE_URL;
            }

            images.add(contentUrl);
        }

        return BingImageSearch.builder()
                .imgUrls(images)
                .build();
    }

}
