package com.me.web.service;

import com.me.web.models.Reading;

public interface ReadingService {
    void addReading(Reading reading);

    Reading getReadingByVin(String vin);
}
