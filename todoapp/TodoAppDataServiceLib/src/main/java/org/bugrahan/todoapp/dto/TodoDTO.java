package org.bugrahan.todoapp.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class TodoDTO {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;
}
