package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsApiModel;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.WorldNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class WorldNewsController {

    @Autowired
    private final WorldNewsService worldNewsService;


    @GetMapping("/news/{cityName}")
    public List<WorldNewsApiModel> getNews(@PathVariable String cityName){
        System.out.println(worldNewsService.getNews(cityName));
        return worldNewsService.getNews(cityName);
    }
}
