package org.bugrahan.todoapp;

import org.bugrahan.todoapp.constant.TestConstant;
import org.bugrahan.todoapp.entity.Todo;
import org.bugrahan.todoapp.mapper.MapperInject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class TodoAppUpdateCityTest {
    @Autowired
    private TodoAppDataService m_dataService;

    @Autowired
    private MapperInject m_mapperInject;

    @Test
    public void givenValue_whenId_thenFound()
    {
        var name = "TodoApp test";
        var id = 6L;
        var todo = new Todo(id, name);

        var oldTodo = "Palhais";

        var updateTodoDTO = m_mapperInject.getTodoMapper().toTodoUpdateDTO(todo);

        var updatedCity = m_dataService.updateTodo(updateTodoDTO);

        assertEquals(name, updatedCity.getName());
    }
}
