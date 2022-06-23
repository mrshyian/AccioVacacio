package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BingImageSearch;
import com.codecool.travelhelper.API.rapidapi.webclients.bingimagesearch.BingImageSearchClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BingImageSearchService {
    @Autowired
    private BingImageSearchClientImpl bingImageSearchClient;

    public BingImageSearch getImg(String searchText, int howMuchImages){
        return bingImageSearchClient.getImagesUrl(searchText, howMuchImages);
    }
}
