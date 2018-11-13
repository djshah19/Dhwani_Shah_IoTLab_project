package com.me.web.controller;

import com.me.web.models.Alert;
import com.me.web.models.Vehicle;
import com.me.web.service.AlertService;
import com.me.web.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AlertService alertService;

    @CrossOrigin
    @PutMapping("/vehicles")
    public void create(@RequestBody Vehicle[] vehicle)
    {
        for(Vehicle veh : vehicle){
        Vehicle vh = this.vehicleService.getVehicleByVin(veh.getVin());
        if(vh!=null)
        {
        veh.setId(vh.getId());
        this.vehicleService.updateVehicle(veh);
        }else {
        this.vehicleService.addVehicle(veh);}}
    }

    @CrossOrigin
    @GetMapping("/vehicles/get/all")
    public List<Vehicle> getAllVehicles(){
        return this.vehicleService.getAllVehicle();
    }

    @CrossOrigin
    @GetMapping("/vehicles/{vin}/alerts")
    public List<Alert> getAllAlerts(@PathVariable("vin") String vin){
        return this.alertService.getAllAlertByVin(vin);
    }

    @CrossOrigin
    @GetMapping("/vehicles/get/alerts")
    public List<Alert> getHighAlerts(){
        return  this.alertService.getHighAlert();
    }

}
