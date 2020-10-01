package com.cutting.manager;

import com.cutting.manager.models.repositories.LocationRepository;
import com.cutting.manager.models.repositories.MetalSheetRepository;
import com.cutting.manager.models.repositories.TypeRepository;
import com.cutting.manager.models.responses.LocationFxModel;
import com.cutting.manager.models.responses.MetalSheetFxModel;
import com.cutting.manager.models.responses.TypeFxModel;
import com.cutting.manager.models.services.LocationService;
import com.cutting.manager.models.services.MetalSheetService;
import com.cutting.manager.models.services.TypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class MetalSheetServiceTests {

    @Autowired
    private MetalSheetService metalSheetService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private MetalSheetRepository repository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private TypeRepository typeRepository;

    @BeforeEach
    void initializeDatabase() {
        final LocationFxModel locationFxModel = new LocationFxModel("location");
        locationService.add(locationFxModel);
        final TypeFxModel typeFxModel = new TypeFxModel("type","info");
        typeService.add(typeFxModel);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        locationRepository.deleteAll();
        typeRepository.deleteAll();
    }

    @Test
    void deleteMetalSheetModel() {
        final MetalSheetFxModel model = new MetalSheetFxModel(3000D,1500D,3D,1,"type","location");

        assertEquals(0,repository.count());
        metalSheetService.add(model);
        assertEquals(1,repository.count());
        metalSheetService.deleteById(15L);
        assertEquals(0,repository.count());
    }

    @Test
    void updateLength() {
        final MetalSheetFxModel model = new MetalSheetFxModel(3000D,1500D,3D,1,"type","location");

        assertEquals(0,repository.count());
        metalSheetService.add(model);
        assertEquals(1,repository.count());
        metalSheetService.updateLength(3L,"4000.0");
        assertEquals(4000D,repository.getById(3L).getLength());
    }

    @Test
    void updateWidth() {
        final MetalSheetFxModel model = new MetalSheetFxModel(3000D,1500D,3D,1,"type","location");

        assertEquals(0,repository.count());
        metalSheetService.add(model);
        assertEquals(1,repository.count());
        metalSheetService.updateWidth(6L,"2000.0");
        assertEquals(2000D,repository.getById(6L).getWidth());
    }

    @Test
    void updateThickness() {
        final MetalSheetFxModel model = new MetalSheetFxModel(3000D,1500D,3D,1,"type","location");

        assertEquals(0,repository.count());
        metalSheetService.add(model);
        assertEquals(1,repository.count());
        metalSheetService.updateThickness(12L,"2000.0");
        assertEquals(2000D,repository.getById(12L).getThickness());
    }

    @Test
    void updateQuantity() {
        final MetalSheetFxModel model = new MetalSheetFxModel(3000D,1500D,3D,1,"type","location");

        assertEquals(0,repository.count());
        metalSheetService.add(model);
        assertEquals(1,repository.count());
        metalSheetService.updateQuantity(9L,"2");
        assertEquals(2,repository.getById(9L).getQuantity());
    }
}
