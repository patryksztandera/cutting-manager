package com.cutting.manager;

import com.cutting.manager.models.repositories.MetalSheetRepository;
import com.cutting.manager.models.responses.LocationFxModel;
import com.cutting.manager.models.responses.MetalSheetFxModel;
import com.cutting.manager.models.responses.TypeFxModel;
import com.cutting.manager.models.services.LocationService;
import com.cutting.manager.models.services.MetalSheetService;
import com.cutting.manager.models.services.TypeService;
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

    @BeforeEach
    void initializeDatabase() {
        final LocationFxModel locationFxModel = new LocationFxModel("location");
        locationService.add(locationFxModel);
        final TypeFxModel typeFxModel = new TypeFxModel("type","info");
        typeService.add(typeFxModel);
    }

    @Test
    void deleteMetalSheetModel() {
        final MetalSheetFxModel model = new MetalSheetFxModel(3000D,1500D,3D,"type","location");

        assertEquals(0,repository.count());
        metalSheetService.add(model);
        assertEquals(1,repository.count());
        metalSheetService.deleteById(3L);
        assertEquals(0,repository.count());
    }
}
