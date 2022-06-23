package com.codecool.travelhelper.API.rapidapi.webclients.bingimagesearch;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BingImageSearch;

public interface BingImageSearchClient {
    BingImageSearch getImagesUrl(String searchText, int howMuchImages);
}
