package com.me.web.service;

import com.me.web.models.Vehicle;
import com.me.web.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void addVehicle(Vehicle vehicle){
        this.vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        this.vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleByVin(String vin)
    {
        return this.vehicleRepository.findVehicleByVin(vin);
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return this.vehicleRepository.findAll();
    }
}
