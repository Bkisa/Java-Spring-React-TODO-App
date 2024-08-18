package org.bugrahan.todoapp.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "m_name")
@ToString
public class Todo {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;

    @Accessors(prefix = "m_")
    private boolean m_isCompleted;

    @Accessors(prefix = "m_")
    private LocalDate m_endDate;

    @Accessors(prefix = "m_")
    private User m_user;
}
