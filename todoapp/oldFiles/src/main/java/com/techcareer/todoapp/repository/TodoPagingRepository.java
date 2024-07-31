package com.techcareer.todoapp.repository;

import com.techcareer.todoapp.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
@Lazy
@Slf4j
public class TodoPagingRepository implements ITodoPagingRepository {
    private static final String SAVE_SQL = "INSERT INTO todo (name) values (:name)";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM todo where id = :id";
    private static final String FIND_ALL_SQL = "SELECT * FROM todo";
    private static final String DELETE_BY_ID_SQL = "delete * from todo where id= :id";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM todo where name = :name";
    private static final String UPDATE_SQl = "";

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public TodoPagingRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //////////////////////////////////////////////////////////////
    @Override
    public List<Todo> findAllByUsername(String username, Pageable pageable)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, Pageable pageable)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Todo entity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAll(Iterable<? extends Todo> entities)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long aLong)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsById(Long aLong)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<Todo> findAll()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<Todo> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Todo> findById(Long aLong)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Todo> S save(S entity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends Todo> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Todo> findAllByUsername(String username, org.springframework.data.domain.Pageable pageable)
    {
        return List.of();
    }

    @Override
    public List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, org.springframework.data.domain.Pageable pageable)
    {
        return List.of();
    }
}

