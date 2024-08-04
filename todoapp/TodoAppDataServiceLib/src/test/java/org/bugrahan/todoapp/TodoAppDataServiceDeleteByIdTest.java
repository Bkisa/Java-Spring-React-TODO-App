package org.bugrahan.todoapp;

import org.bugrahan.todoapp.constant.TestConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootApplication
@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
@Import(TodoAppDataService.class)
public class TodoAppDataServiceDeleteByIdTest {
    @Autowired
    private TodoAppDataService m_todoAppDataService;

    @Test
    public void testDeleteCityById()
    {
        var cityId = 12L;

        m_todoAppDataService.deleteById(cityId);
        var cityOpt = m_todoAppDataService.findTodoById(cityId);

        assertTrue(cityOpt.isEmpty());
    }
}
