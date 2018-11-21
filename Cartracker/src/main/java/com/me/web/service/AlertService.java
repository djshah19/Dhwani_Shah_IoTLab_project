package com.me.web.service;

import com.me.web.models.Alert;

import java.util.List;

public interface AlertService {
    void insertAlert(Alert alert);

    List<Alert> getAllAlertByVin(String vin);

    List<Alert> getHighAlert();
}
