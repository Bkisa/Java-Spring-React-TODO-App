package org.bugrahan.todoapp;

import org.bugrahan.todoapp.constant.TestConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class TodoAppFindAllCompletedTest {
    @Autowired
    private TodoAppDataService m_todoAppDataService;

    @Test
    public void testFindAllCompleted()
    {
        var expectedCount = 11L;  // Bu değeri test veritabanınızda tamamlanmış görev sayısına göre güncelleyin
        var count = StreamSupport.stream(m_todoAppDataService.findAllCompleted().spliterator(), false).count();

        assertEquals(expectedCount, count);
    }
}
