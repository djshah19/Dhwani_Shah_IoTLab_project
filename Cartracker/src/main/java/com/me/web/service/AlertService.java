package com.me.web.service;

import com.me.web.models.Alert;
import com.me.web.models.Vehicle;

import java.util.List;

public interface AlertService {
    void insertAlert(Alert alert);
    List<Alert> getAllAlertByVin(String vin);
    List<Alert> getHighAlert();
}
