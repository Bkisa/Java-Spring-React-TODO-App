package org.bugrahan.todoapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.bugrahan.todoapp.entity.Todo;
import org.bugrahan.todoapp.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Lazy
@Slf4j
public class TodoRepository implements ITodoRepository, IUserRepository {
    private static final String DELETE_BY_ID_SQL = "call sp_delete_todo_by_id(:id)";
    private static final String FIND_ALL_SQL = "select * from find_all_todos()";
    private static final String FIND_BY_ID_SQL = "select * from find_todo_by_id(:id)";
    private static final String FIND_BY_NAME_SQL = "select * from find_todo_by_name(:name)";
    private static final String SAVE_SQL = "select * from insert_todo(:name, :completed, :endDate)";
    private static final String UPDATE_SQL = "call sp_update_todo(:id, :name, :completed, :endDate)";
    private static final String FIND_ALL_COMPLETED_SQL = "select * from find_all_completed()";
    private static final String FIND_ALL_NOT_COMPLETED_SQL = "select * from find_all_not_completed()";
    private static final String DELETE_COMPLETED_SQL = "call sp_delete_completed_todos()";
    private static final String DELETE_ALL_SQL = "call sp_delete_all()";
    private static final String FIND_BY_END_DATE_SQL = "select * from find_todo_by_end_date(:endDate)";

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public TodoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private void fillTodos(ArrayList<Todo> todos, ResultSet rs) throws SQLException
    {
        do {
            User user = new User();
            user.setId(rs.getInt("user_id"));

            todos.add(new Todo(
                    rs.getLong("todo_id"),
                    rs.getString("name"),
                    rs.getBoolean("completed"),
                    rs.getObject("end_date", LocalDate.class),
                    user
            ));
        } while (rs.next());
    }


    /* Admins Users */
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
        paramMap.put("endDate", todo.getEndDate());
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
        paramMap.put("endDate", todo.getEndDate());
        m_namedParameterJdbcTemplate.update(UPDATE_SQL, paramMap);

        return todo;
    }

    @Override
    public Iterable<Todo> findAllCompleted()
    {
        log.info("TodoRepository.findAllCompleted");
        var todos = new ArrayList<Todo>();

        m_namedParameterJdbcTemplate.query(FIND_ALL_COMPLETED_SQL, rs -> {fillTodos(todos, rs);});
        return todos;
    }

    @Override
    public Iterable<Todo> findAllNotCompleted()
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

    @Override
    public List<Todo> findByEndDate(LocalDate endDate)
    {
        log.info("TodoRepository.findByEndDate: -> endDate:{}", endDate);
        var todos = new ArrayList<Todo>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("endDate", endDate);

        m_namedParameterJdbcTemplate.query(FIND_BY_END_DATE_SQL, paramMap, (ResultSet rs) -> {fillTodos(todos, rs);});

        return todos;
    }


    /* Normal Users */
    @Override
    public Iterable<Todo> findAllByIdAndName(long id, String name)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Iterable<Todo> findCompletedById(long id)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Iterable<Todo> findNotCompletedById(long id)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Iterable<Todo> deleteNotCompletedById(long id)
    {
        throw new UnsupportedOperationException("TODO");
    }

    /////////////////////////////////////////////////

    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void delete(Todo entity)
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void deleteAll(Iterable<? extends Todo> entities)
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs)
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public boolean existsById(Long aLong)
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public Iterable<Todo> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public <S extends Todo> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
