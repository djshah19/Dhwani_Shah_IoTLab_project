package com.me.web.controller;

import com.me.web.models.Alert;
import com.me.web.models.Reading;
import com.me.web.models.Vehicle;
import com.me.web.service.AlertService;
import com.me.web.service.ReadingService;
import com.me.web.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ReadingController {
    @Autowired
    private ReadingService readingService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AlertService alertService;

    @CrossOrigin
    @PostMapping("/readings")
    public void addReading(@RequestBody Reading reading){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Vehicle vehicle = this.vehicleService.getVehicleByVin(reading.getVin());
        if(vehicle==null){
            return;
        }
        if(reading.getEngineRpm() > vehicle.getRedlineRpm()){
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setMessage("EngineRPM > RedLineRPM");
            alert.setTimeStamp(String.valueOf(timestamp));
            alert.setPriority("HIGH");
            this.alertService.insertAlert(alert);
        }
        if(reading.getFuelVolume()<((vehicle.getMaxFuelVolume()*10f)/100)){
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setMessage("FuelVolume < 10% of MaxFuelVolume");
            alert.setTimeStamp(String.valueOf(timestamp));
            alert.setPriority("MEDIUM");
            this.alertService.insertAlert(alert);
        }
        if((reading.getTires().getFrontRight() < 32 || reading.getTires().getFrontRight() > 36  ) ||
           (reading.getTires().getFrontLeft() < 32 || reading.getTires().getFrontLeft() > 36  ) ||
           (reading.getTires().getRearRight() < 32 || reading.getTires().getRearRight() > 36  )  ||
           (reading.getTires().getRearLeft() < 32 || reading.getTires().getRearLeft() > 36  )  ){
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setMessage("tire pressure of any tire < 32psi or > 36psi");
            alert.setTimeStamp(String.valueOf(timestamp));
            alert.setPriority("LOW");
            this.alertService.insertAlert(alert);
        }
        this.readingService.addReading(reading);
    }
}
