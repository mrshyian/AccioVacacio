package com.codecool.travelhelper.API.simple.webclient.emergency_numbers;


import com.codecool.travelhelper.API.simple.model.EmergencyNumbersDto;
import com.codecool.travelhelper.API.simple.webclient.emergency_numbers.dto.OpenEmergencyNumbersEmergencyNumbersDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmergencyNumbersClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String WEATHER_URL = "https://emergencynumberapi.com/api/country/";

    public EmergencyNumbersDto getEmergencyNumbers(String countryCode) {
        OpenEmergencyNumbersEmergencyNumbersDto openEmergencyNumbersEmergencyNumbersDto =  callGetMethode(countryCode,
                OpenEmergencyNumbersEmergencyNumbersDto.class);


        return EmergencyNumbersDto.builder()
                .ambulance(openEmergencyNumbersEmergencyNumbersDto.getData().getAmbulance().getAll().get(0))
                .police(openEmergencyNumbersEmergencyNumbersDto.getData().getPolice().getAll().get(0))
                .fireGuard(openEmergencyNumbersEmergencyNumbersDto.getData().getFire().getAll().get(0))
                .dispatch(openEmergencyNumbersEmergencyNumbersDto.getData().getDispatch().getAll().get(0))
                .build();
    }

    public <T> T callGetMethode(String url, Class<T> responseType) {
        System.out.println(WEATHER_URL + url);
        return restTemplate.getForObject(WEATHER_URL + url, responseType);
    }
}
