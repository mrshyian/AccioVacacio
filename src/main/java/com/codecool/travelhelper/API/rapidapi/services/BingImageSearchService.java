package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BingImageSearch;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.bingimagesearch.BingImageSearchClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BingImageSearchService {
    @Autowired
    private BingImageSearchClientImpl bingImageSearchClient;

    public BingImageSearch getImg(String searchText, int howMuchImages){
        return bingImageSearchClient.getImagesUrl(searchText, howMuchImages);
    }
}
