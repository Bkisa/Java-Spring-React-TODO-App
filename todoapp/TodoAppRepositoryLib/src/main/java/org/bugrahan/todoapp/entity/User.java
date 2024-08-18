package org.bugrahan.todoapp.entity;

import lombok.*;
import lombok.experimental.Accessors;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_username;

    @Accessors(prefix = "m_")
    private String m_password;

    @Accessors(prefix = "m_")
    private String m_role;

    @Accessors(prefix = "m_")
    private Set<Todo> m_todos;
}

