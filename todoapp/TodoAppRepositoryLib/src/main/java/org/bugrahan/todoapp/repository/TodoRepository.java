package org.bugrahan.todoapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.bugrahan.todoapp.entity.Todo;
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
    private static final String DELETE_BY_ID_SQL = "call sp_delete_todo_by_id(:id)";
    private static final String FIND_ALL_SQL = "select * from find_all_todos()";
    private static final String FIND_BY_ID_SQL = "select * from find_todo_by_id(:id)";
    private static final String FIND_BY_NAME_SQL = "select * from find_todo_by_name(:name)";
    private static final String SAVE_SQL = "select * from insert_todo(:name, :completed)";
    private static final String UPDATE_SQL = "call sp_update_todo(:id, :name, :completed)";
    private static final String FIND_ALL_COMPLETED_SQL = "select * from todos where completed = true"; // Yeni SQL
    private static final String FIND_ALL_NOT_COMPLETED_SQL = "select * from todos where completed = false"; // Yeni SQL
    private static final String DELETE_COMPLETED_SQL = "call sp_delete_completed_todos()";
    private static final String DELETE_ALL_SQL = "call sp_delete_all()";

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public TodoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private void fillTodos(ArrayList<Todo> todos, ResultSet rs) throws SQLException
    {
        do {
            todos.add(new Todo(rs.getLong(1), rs.getString(2), rs.getBoolean(3)));
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

        m_namedParameterJdbcTemplate.query(FIND_BY_NAME_SQL, paramMap, (ResultSet rs) -> {fillTodos(todos, rs);});

        return todos;
    }

    @Override
    public <S extends Todo> S save(S todo)
    {
        log.info("TodoRepository.save: -> todo:{}", todo.toString());
        var paramMap = new HashMap<String, Object>();

        paramMap.put("name", todo.getName());
        paramMap.put("completed", todo.isCompleted());
        m_namedParameterJdbcTemplate.query(SAVE_SQL, paramMap, (ResultSet rs) -> todo.setId(rs.getLong(1)));

        return todo;
    }

    @Override
    public Todo updateTodo(Todo todo)
    {
        log.info("TodoRepository.updateTodo: -> todo:{}", todo.toString());
        var paramMap = new HashMap<String, Object>();

        paramMap.put("id", todo.getId());
        paramMap.put("name", todo.getName());
        paramMap.put("completed", todo.isCompleted());
        m_namedParameterJdbcTemplate.update(UPDATE_SQL, paramMap);

        return todo;
    }

    @Override
    public Iterable<Todo> findAllCompleted() // Yeni yöntem
    {
        log.info("TodoRepository.findAllCompleted");
        var todos = new ArrayList<Todo>();

        m_namedParameterJdbcTemplate.query(FIND_ALL_COMPLETED_SQL, rs -> {fillTodos(todos, rs);});
        return todos;
    }

    @Override
    public Iterable<Todo> findAllNotCompleted() // Yeni yöntem
    {
        log.info("TodoRepository.findAllNotCompleted");
        var todos = new ArrayList<Todo>();

        m_namedParameterJdbcTemplate.query(FIND_ALL_NOT_COMPLETED_SQL, rs -> {fillTodos(todos, rs);});
        return todos;
    }

    @Override
    public void deleteCompletedTodos()
    {
        log.info("TodoRepository.deleteCompletedTodos");
        m_namedParameterJdbcTemplate.update(DELETE_COMPLETED_SQL, new HashMap<>());
    }

    @Override
    public void deleteAllTodos()
    {
        log.info("TodoRepository.deleteAllTodos");
        m_namedParameterJdbcTemplate.update(DELETE_ALL_SQL, new HashMap<>());
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
