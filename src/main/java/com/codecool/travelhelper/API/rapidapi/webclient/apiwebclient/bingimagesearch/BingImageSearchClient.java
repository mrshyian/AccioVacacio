package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.bingimagesearch;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BingImageSearch;

public interface BingImageSearchClient {
    BingImageSearch getImagesUrl(String searchText, int howMuchImages);
}
