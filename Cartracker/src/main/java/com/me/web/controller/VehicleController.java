package com.me.web.controller;

import com.me.web.models.Alert;
import com.me.web.models.Vehicle;
import com.me.web.service.AlertService;
import com.me.web.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AlertService alertService;

    // save/update vehicle details
    @CrossOrigin
    @PutMapping("/vehicles")
    public void create(@RequestBody Vehicle[] vehicle) {
        for (Vehicle veh : vehicle) {
            Vehicle vh = this.vehicleService.getVehicleByVin(veh.getVin());
            if (vh != null) {
                veh.setId(vh.getId());
                this.vehicleService.updateVehicle(veh);
            } else {
                this.vehicleService.addVehicle(veh);
            }
        }
    }

    // return all vehicles details
    @CrossOrigin
    @GetMapping("/vehicles/get/all")
    public List<Vehicle> getAllVehicles() {
        LOGGER.info("Get all vehicles");
        return this.vehicleService.getAllVehicle();
    }

    // return all alerts for a particular vehicle
    @CrossOrigin
    @GetMapping("/vehicles/{vin}/alerts")
    public List<Alert> getAllAlerts(@PathVariable("vin") String vin) {
        LOGGER.info("Get all alerts by vin");
        return this.alertService.getAllAlertByVin(vin);
    }

    // return all alerts within last 2 hours
    @CrossOrigin
    @GetMapping("/vehicles/get/alerts")
    public List<Alert> getHighAlerts() {
        LOGGER.info("Get all high alerts within last 2 hours");
        return this.alertService.getHighAlert();
    }

}
