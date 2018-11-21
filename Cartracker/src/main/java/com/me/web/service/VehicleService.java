package com.me.web.service;

import com.me.web.models.Vehicle;

import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle);

    void updateVehicle(Vehicle vehicle);

    Vehicle getVehicleByVin(String vin);

    List<Vehicle> getAllVehicle();
}
