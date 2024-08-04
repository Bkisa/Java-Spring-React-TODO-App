package org.bugrahan.todoapp.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class TodoSaveDTO {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;

    @Accessors(prefix = "m_")
    private boolean m_isCompleted;
}
