package com.me.web.service;

import com.me.web.models.Reading;
import com.me.web.repositories.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingServiceImpl implements ReadingService {
    @Autowired
    private ReadingRepository readingRepository;

    @Override
    public void addReading(Reading reading) {
        this.readingRepository.save(reading);
    }

    @Override
    public Reading getReadingByVin(String vin) {
        return this.readingRepository.findReadingByVin(vin);
    }
}
