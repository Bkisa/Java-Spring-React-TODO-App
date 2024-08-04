package org.bugrahan.todoapp.repository;

import org.bugrahan.todoapp.constant.TestConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class TodoRepositoryDeleteCompletedTest {
    @Autowired
    private ITodoRepository m_todoRepository;

    @Test
    public void test()
    {
        var expectedCount = 0L;

        m_todoRepository.deleteCompletedTodos();

        var count = StreamSupport.stream(m_todoRepository.findAllCompleted().spliterator(), false).count();

        assertEquals(expectedCount, count);
    }
}
