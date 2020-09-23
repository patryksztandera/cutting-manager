package com.cutting.manager;

import com.cutting.manager.models.repositories.LocationRepository;
import com.cutting.manager.models.responses.LocationFxModel;
import com.cutting.manager.models.services.LocationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class LocationServiceTests {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void deleteByLocation() {
        final LocationFxModel model = new LocationFxModel("location");

        assertEquals(0,repository.count());
        locationService.add(model);
        assertEquals(1,repository.count());
        locationService.delete(model.getLocation());
        assertEquals(0,repository.count());
    }

    @Test
    void updateByLocation() {
        final LocationFxModel model = new LocationFxModel("location");

        assertEquals(0,repository.count());
        locationService.add(model);
        assertEquals(1,repository.count());
        locationService.updateLocation("location","new");
        assertEquals("new", locationService.getLocations().get(0));
    }
}
