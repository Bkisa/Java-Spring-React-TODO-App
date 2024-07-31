package com.techcareer.todoapp.repository;

import com.techcareer.todoapp.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Lazy
@Slf4j
public class TodoRepository implements ITodoRepository {





    ////////////////////////////////////////////////////////////////////
    @Override
    public List<Todo> findAllByUsername(String username)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Todo findByUsernameAndId(String username, long Id)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long countByUsername(String username)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long countByUsernameAndIsCompleted(String username, boolean isCompleted)
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
}

