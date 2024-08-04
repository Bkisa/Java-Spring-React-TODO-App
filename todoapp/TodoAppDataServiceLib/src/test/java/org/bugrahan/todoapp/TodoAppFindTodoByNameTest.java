package org.bugrahan.todoapp;

import org.bugrahan.todoapp.constant.TestConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoAppFindTodoByNameTest {
    @Autowired
    private TodoAppDataService m_todoAppDataService;

    @Test
    public void givenName_whenCityFound_thenReturnCity() {
        var name = "Bailieborough";
        var cityOpt = m_todoAppDataService.findTodoByName(name).get().getName();

        assertEquals(cityOpt, name);
    }

    @Test
    public void givenNonExistentName_whenCityNotFound_thenReturnEmpty() {
        var name = "NonExistentCity";
        var cityOpt = m_todoAppDataService.findTodoByName(name);

        assertTrue(cityOpt.isEmpty());
    }
}
