package com.me.web.service;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.me.web.models.Alert;
import com.me.web.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{
    @Autowired
    AlertRepository alertRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insertAlert(Alert alert) {
        this.alertRepository.save(alert);
    }

    @Override
    public List<Alert> getAllAlertByVin(String vin) {
        return this.alertRepository.findAllAlertByVin(vin);
    }

    @Override
    public List<Alert> getHighAlert() {
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -2);
        Timestamp timeStamp1 = new Timestamp(cal.getTime().getTime());
        Query query = new Query();
        query.addCriteria(Criteria.where("priority").is("HIGH").andOperator(Criteria.where("timeStamp").lte(String.valueOf(timeStamp)),
                Criteria.where("timeStamp").gte(String.valueOf(timeStamp1))));
        List<Alert> alerts = mongoTemplate.find(query, Alert.class);
        return alerts;
    }
}
