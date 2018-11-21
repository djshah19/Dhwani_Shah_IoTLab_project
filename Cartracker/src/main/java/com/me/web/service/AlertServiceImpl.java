package com.me.web.service;

import com.me.web.models.Alert;
import com.me.web.repositories.AlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertServiceImpl.class);
    @Autowired
    AlertRepository alertRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    @CacheEvict(value = "alert", allEntries = true)
    public void insertAlert(Alert alert) {
        this.alertRepository.save(alert);
    }

    @Override
    @Cacheable(value = "alert")
    public List<Alert> getAllAlertByVin(String vin) {
        LOGGER.info("Get all alerts by vin");
        return this.alertRepository.findAllAlertByVin(vin);
    }

    @Override
    @Cacheable(value = "alert")
    public List<Alert> getHighAlert() {
        LOGGER.info("Get all high alerts within last 2 hours");
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
