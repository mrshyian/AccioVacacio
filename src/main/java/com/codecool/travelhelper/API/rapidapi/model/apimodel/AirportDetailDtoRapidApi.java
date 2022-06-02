package com.codecool.travelhelper.API.rapidapi.model.apimodel;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportDetailDtoRapidApi {
    String name;
    String location;
    String streetNumber;
    String street;
    String city;
    String state;
    String phone;
    String website;

    @Override
    public String toString() {
        return "AirportDetailDtoRapidApi{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
//    "name": "Warsaw Modlin Airport",
//    "location": "Warsaw, Poland",
//    "street_number": "1a",
//    "street": "Generała Wiktora Thommée",
//    "city": "Nowy Dwór Mazowiecki",
//    "county": "nowodworski",
//    "state": "mazowieckie",
//    "country_iso": "PL",
//    "country": "Poland",
//    "postal_code": "05-102",
//    "phone": "+48 22 315 18 80",
//    "latitude": 52.449265,
//    "longitude": 20.651237,
//    "uct": 120,
//    "website": "http://www.modlinairport.pl/"