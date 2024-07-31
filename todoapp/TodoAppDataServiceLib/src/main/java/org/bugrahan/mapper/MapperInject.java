package org.bugrahan.mapper;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MapperInject {
    @Accessors(prefix = "m_")
    private final ITodoMapper m_todoMapper;

    public MapperInject(ITodoMapper todoMapper)
    {
        m_todoMapper = todoMapper;
    }
}
