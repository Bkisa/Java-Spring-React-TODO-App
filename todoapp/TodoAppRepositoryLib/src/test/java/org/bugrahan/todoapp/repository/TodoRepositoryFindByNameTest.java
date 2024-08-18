package org.bugrahan.todoapp.repository;

import org.bugrahan.todoapp.constant.TestConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class TodoRepositoryFindByNameTest {
    @Autowired
    private ITodoRepository m_todoRepository;

    @Test
    public void givenValue_whenName_thenFound()
    {
        var name = "Zhuli";
        var expectedCount = 1L;
        var count = StreamSupport.stream(m_todoRepository.findByName(name).spliterator(), false).count();

        assertEquals(expectedCount, count);
    }
}
