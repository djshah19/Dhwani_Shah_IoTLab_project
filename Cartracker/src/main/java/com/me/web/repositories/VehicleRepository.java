package com.me.web.repositories;

import com.me.web.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Vehicle findVehicleByVin(String vin);
}
