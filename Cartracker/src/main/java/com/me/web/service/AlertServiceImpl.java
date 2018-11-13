package com.me.web.service;

import com.me.web.models.Alert;
import com.me.web.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{
    @Autowired
    AlertRepository alertRepository;

    @Override
    public void insertAlert(Alert alert) {
        this.alertRepository.save(alert);
    }

    @Override
    public List<Alert> getAllAlertByVin(String vin) {
        return this.alertRepository.findAllAlertByVin(vin);
    }
}
