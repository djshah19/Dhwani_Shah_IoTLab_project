package com.me.web.controller;

import com.me.web.models.Vehicle;
import com.me.web.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

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

}
