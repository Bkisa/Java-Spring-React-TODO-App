package org.bugrahan.todoapp.configuration;

import org.bugrahan.todoapp.TodoAppDataService;
import org.bugrahan.todoapp.dal.TodoAppHelper;
import org.bugrahan.todoapp.mapper.ITodoMapper;
import org.bugrahan.todoapp.mapper.MapperInject;
import org.bugrahan.todoapp.mapper.TodoMapperImpl;
import org.bugrahan.todoapp.repository.ITodoRepository;
import org.bugrahan.todoapp.repository.TodoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//@Configuration
public class TestConfig {

//    @Bean
//    public ITodoRepository todoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
//    {
//        return new TodoRepository(namedParameterJdbcTemplate);
//    }
//
//    @Bean
//    public TodoAppHelper flightSystemDataHelper(ITodoRepository todoRepository)
//    {
//        return new TodoAppHelper(todoRepository);
//    }
//
//    @Bean
//    public ITodoMapper todoMapper()
//    {
//        return new TodoMapperImpl();
//    }
//
//    @Bean
//    public MapperInject mapperInject(ITodoMapper todoMapper)
//    {
//        return new MapperInject(todoMapper);
//    }
//
//    @Bean
//    public TodoAppDataService todoAppDataService(TodoAppHelper todoAppHelper, MapperInject mapperInject)
//    {
//        return new TodoAppDataService(todoAppHelper, mapperInject);
//    }
}
