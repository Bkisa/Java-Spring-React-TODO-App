package org.bugrahan.todoapp.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class TodoUpdateDTO {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;

    @Accessors(prefix = "m_")
    private boolean m_isCompleted;

    @Accessors(prefix = "m_")
    private LocalDate m_endDate;
}
