package com.me.web.service;

import com.me.web.models.Vehicle;
import com.me.web.repositories.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @CacheEvict(value = "vehicle", allEntries = true)
    public void addVehicle(Vehicle vehicle) {
        this.vehicleRepository.save(vehicle);
    }

    @Override
    @CacheEvict(value = "vehicle", allEntries = true)
    public void updateVehicle(Vehicle vehicle) {
        this.vehicleRepository.save(vehicle);
    }

    @Override
    @Cacheable(value = "vehicle")
    public Vehicle getVehicleByVin(String vin) {
        LOGGER.info("Get vehicle by vin");
        return this.vehicleRepository.findVehicleByVin(vin);
    }


    @Override
    @Cacheable(value = "vehicle")
    public List<Vehicle> getAllVehicle() {
        LOGGER.info("Get all vehicles");
        return this.vehicleRepository.findAll();
    }
}
