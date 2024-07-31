package org.bugrahan.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TodoSaveDTO {
    @Accessors(prefix = "m_")
    private String m_name;
}
