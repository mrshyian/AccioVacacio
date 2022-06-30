package com.codecool.travelhelper.API.rapidapi.webclients.bingimagesearch;

import com.codecool.travelhelper.API.rapidapi.models.BingImageSearch;

public interface BingImageSearchClient {
    BingImageSearch getImagesUrl(String searchText, int howMuchImages);
}
