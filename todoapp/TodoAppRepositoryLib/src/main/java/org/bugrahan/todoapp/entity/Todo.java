package org.bugrahan.todoapp.entity;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "m_name")
@ToString
public class Todo {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;
}
