package org.bugrahan.todoapp.repository;

import org.bugrahan.todoapp.constant.TestConstant;
import org.bugrahan.todoapp.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class TodoRepositorySaveTest {
    @Autowired
    private ITodoRepository m_todoRepository;

    @Test
    public void givenValue_whenCity_thenSetIdentity()
    {
        var todo = new Todo(51, "Test Data");
        var expectedId = 51L;

        m_todoRepository.save(todo);

        assertEquals(expectedId, todo.getId());
    }
}
