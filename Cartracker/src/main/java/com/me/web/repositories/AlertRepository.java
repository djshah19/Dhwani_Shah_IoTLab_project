package com.me.web.repositories;

import com.me.web.models.Alert;
import com.me.web.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlertRepository extends MongoRepository<Alert, String> {
    List<Alert> findAllAlertByVin(String vin);
}
