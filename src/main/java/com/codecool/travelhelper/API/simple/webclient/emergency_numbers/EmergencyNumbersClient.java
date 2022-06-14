package com.codecool.travelhelper.API.simple.webclient.emergency_numbers;


import com.codecool.travelhelper.API.simple.model.EmergencyNumbersDto;
import com.codecool.travelhelper.API.simple.webclient.emergency_numbers.dto.OpenEmergencyNumbersEmergencyNumbersDto;
import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.EmergencyNumbersRepository;
import com.codecool.travelhelper.aws.database.repositories.user_page_repositories.UserRepository;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.EmergencyNumbersTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmergencyNumbersClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String WEATHER_URL = "https://emergencynumberapi.com/api/country/";
    @Autowired
    EmergencyNumbersRepository emergencyNumbersRepository;

    public EmergencyNumbersDto getEmergencyNumbers(String countryCode, String countryName, String cityName) {
        OpenEmergencyNumbersEmergencyNumbersDto openEmergencyNumbersEmergencyNumbersDto =  callGetMethode(countryCode,
                OpenEmergencyNumbersEmergencyNumbersDto.class);

        String ambulanceNumber = openEmergencyNumbersEmergencyNumbersDto.getData().getAmbulance().getAll().get(0);
        String policeNumber = openEmergencyNumbersEmergencyNumbersDto.getData().getPolice().getAll().get(0);
        String fireGuardNumber = openEmergencyNumbersEmergencyNumbersDto.getData().getFire().getAll().get(0);
        String dispatchNumber = openEmergencyNumbersEmergencyNumbersDto.getData().getDispatch().getAll().get(0);

//        Long searchingPlaceId = 1L;
        //----------------------------saving emergency numbers to database----------------------------
        emergencyNumbersRepository.save(
                new EmergencyNumbersTable(
                        cityName,
                        countryName,
                        ambulanceNumber,
                        policeNumber,
                        fireGuardNumber,
                        dispatchNumber
                )
        );
        //--------------------------------------------------------------------------------------------

        return EmergencyNumbersDto.builder()
                .ambulance(ambulanceNumber)
                .police(policeNumber)
                .fireGuard(fireGuardNumber)
                .dispatch(dispatchNumber)
                .build();
    }

    public <T> T callGetMethode(String url, Class<T> responseType) {
        System.out.println(WEATHER_URL + url);
        return restTemplate.getForObject(WEATHER_URL + url, responseType);
    }
}
