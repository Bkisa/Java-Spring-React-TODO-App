package org.bugrahan.repository;

import lombok.extern.slf4j.Slf4j;
import org.bugrahan.entity.Todo;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Repository
@Lazy
@Slf4j
public class TodoRepository implements ITodoRepository {
    private static final String FIND_BY_ID_SQL = "SELECT * FROM todo where id = :id";
    private static final String FIND_ALL_SQL = "SELECT * FROM todo";
    private static final String DELETE_BY_ID_SQL = "delete * from todo where id= :id";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM todo where name = :name";
    private static final String UPDATE_SQl = "";
    private static final String SAVE_SQL = "INSERT INTO todo (name, id) VALUES (:name, :id)";

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public TodoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private void fillTodos(ArrayList<Todo> todo, ResultSet rs) throws SQLException
    {
        do {
            todo.add(new Todo(rs.getLong(1), rs.getString(2)));
        } while (rs.next());
    }

    @Override
    public void deleteById(Long id)
    {
        log.info("TodoRepository.deleteById: -> todo_id:{}", id);
        var paramMap = new HashMap<String, Object>();

        paramMap.put("id", id);

        m_namedParameterJdbcTemplate.update(DELETE_BY_ID_SQL, paramMap);
    }

    @Override
    public Iterable<Todo> findAll()
    {
        log.info("TodoRepository.findAll");
        var todos = new ArrayList<Todo>();

        m_namedParameterJdbcTemplate.query(FIND_ALL_SQL, rs -> {fillTodos(todos, rs);});
        return todos;
    }

    @Override
    public Optional<Todo> findById(Long id)
    {
        log.info("TodoRepository.findById: -> todo_id:{}", id);
        var todos = new ArrayList<Todo>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("id", id);

        m_namedParameterJdbcTemplate.query(FIND_BY_ID_SQL, paramMap, (ResultSet rs) -> {fillTodos(todos, rs);});

        return todos.isEmpty() ? Optional.empty() : Optional.of(todos.get(0));
    }

    @Override
    public Iterable<Todo> findByName(String name)
    {
        log.info("TodoRepository.findByName: -> name:{}", name);
        var todos = new ArrayList<Todo>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("name", name);

        m_namedParameterJdbcTemplate.query(FIND_BY_NAME_SQL, paramMap, (ResultSet rs) -> {fillTodos(todos, rs);})   ;

        return todos;
    }

    @Override
    public <S extends Todo> S save(S todo)
    {
        log.info("TodoRepository.save: -> todo:{}", todo.toString());
        var paramMap = new HashMap<String, Object>();

        paramMap.put("name", todo.getName());
        m_namedParameterJdbcTemplate.query(SAVE_SQL, paramMap, (ResultSet rs) -> todo.setId(rs.getLong(1)));

        return todo;
    }

    @Override
    public Todo updateTodo(Todo todo)
    {
        log.info("TodoRepository.updateCity: -> todo:{}", todo.toString());
        var paramMap = new HashMap<String, Object>();

        paramMap.put("id", todo.getId());
        paramMap.put("name", todo.getName());
        m_namedParameterJdbcTemplate.update(UPDATE_SQl, paramMap);

        return todo;
    }


    //////////////////////////////////////////////////////////////////
    @Override
    public long count()
    {
        return 0;
    }

    @Override
    public void delete(Todo entity)
    {

    }

    @Override
    public void deleteAll()
    {

    }

    @Override
    public void deleteAll(Iterable<? extends Todo> entities)
    {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs)
    {

    }

    @Override
    public boolean existsById(Long aLong)
    {
        return false;
    }

    @Override
    public Iterable<Todo> findAllById(Iterable<Long> longs)
    {
        return null;
    }


    @Override
    public <S extends Todo> Iterable<S> saveAll(Iterable<S> entities)
    {
        return null;
    }
}
