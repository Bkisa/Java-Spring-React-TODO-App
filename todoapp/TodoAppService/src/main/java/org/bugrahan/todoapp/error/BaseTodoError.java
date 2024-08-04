package org.bugrahan.todoapp.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BaseTodoError {
    @Accessors(prefix = "m_")
    private String m_errorMessage;

    @Accessors(prefix = "m_")
    private int m_status;

    public BaseTodoError(String errorMessage, int status)
    {
        m_errorMessage = errorMessage;
        m_status = status;
    }
}
