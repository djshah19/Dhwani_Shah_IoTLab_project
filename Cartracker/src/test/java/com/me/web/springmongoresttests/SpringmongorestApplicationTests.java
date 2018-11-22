package com.me.web.springmongoresttests;

import com.me.web.models.Reading;
import com.me.web.models.Tires;
import com.me.web.models.Vehicle;
import com.me.web.service.ReadingService;
import com.me.web.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringmongorestApplicationTests {
    @Autowired
    private ReadingService readingService;

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void saveReadingTest() {
        Reading reading = new Reading();
        reading.setVin("1HGCR2F3XFA027534");
        reading.setLatitude(41.803194);
        reading.setLongitude(-88.144406);
        reading.setTimestamp("2017-05-25T17:31:25.268Z");
        reading.setFuelVolume(1.5f);
        reading.setSpeed(85);
        reading.setEngineHp(240);
        reading.setCheckEngineLightOn(false);
        reading.setEngineCoolantLow(true);
        reading.setCruiseControlOn(true);
        reading.setEngineRpm(6300);
        Tires tires = new Tires();
        tires.setFrontLeft(34);
        tires.setFrontRight(36);
        tires.setRearLeft(29);
        tires.setRearRight(34);
        reading.setTires(tires);

        readingService.addReading(reading);
        assertEquals(reading.getVin(), readingService.getReadingByVin(reading.getVin()).getVin());
    }

    @Test
    public void saveVehicleTest() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("1HGCR2F3XFA027534");
        vehicle.setMake("HONDA");
        vehicle.setModel("ACCORD");
        vehicle.setYear(2015);
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        vehicle.setLastServiceDate("2017-05-25T17:31:25.268Z");

        vehicleService.addVehicle(vehicle);

        assertEquals(vehicle.getVin(), vehicleService.getVehicleByVin(vehicle.getVin()).getVin());

    }

}
