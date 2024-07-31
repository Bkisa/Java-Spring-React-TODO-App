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
public class TodoUpdateDTO {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;
}
