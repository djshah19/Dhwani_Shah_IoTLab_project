package com.me.web.controller;

import com.me.web.models.Reading;
import com.me.web.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReadingController {
    @Autowired
    ReadingService readingService;

    @CrossOrigin
    @PostMapping("/readings")
    public void addReading(@RequestBody Reading reading){
        this.readingService.addReading(reading);
    }
}
