package com.cutting.manager;

import com.cutting.manager.models.repositories.TypeRepository;
import com.cutting.manager.models.responses.TypeFxModel;
import com.cutting.manager.models.services.TypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class TypeServiceTests {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void deleteTypeModel() {
        final TypeFxModel model = new TypeFxModel("type","info");

        assertEquals(0,repository.count());
        typeService.add(model);
        assertEquals(1,repository.count());
        typeService.delete(model.getType());
        assertEquals(0,repository.count());
    }

    @Test
    void updateType() {
        final TypeFxModel model = new TypeFxModel("type","info");

        assertEquals(0,repository.count());
        typeService.add(model);
        assertEquals(1,repository.count());
        typeService.updateType("type", "new");
        assertEquals("new", typeService.getTypes().get(0));
    }
}
