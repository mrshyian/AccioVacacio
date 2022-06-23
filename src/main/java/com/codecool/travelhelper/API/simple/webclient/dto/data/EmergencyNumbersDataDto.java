package com.codecool.travelhelper.API.simple.webclient.dto.data;


import com.codecool.travelhelper.API.simple.webclient.dto.data.ambulance.EmergencyNumbersAmbulanceDto;
import com.codecool.travelhelper.API.simple.webclient.dto.data.dispatch.EmergencyNumbersDispatchDto;
import com.codecool.travelhelper.API.simple.webclient.dto.data.fireGuard.EmergencyNumbersFireGuardDto;
import com.codecool.travelhelper.API.simple.webclient.dto.data.police.EmergencyNumbersPoliceDto;
import lombok.Getter;

@Getter
public class EmergencyNumbersDataDto {
    private EmergencyNumbersAmbulanceDto ambulance;
    private EmergencyNumbersPoliceDto police;
    private EmergencyNumbersFireGuardDto fire;
    private EmergencyNumbersDispatchDto dispatch;
}
