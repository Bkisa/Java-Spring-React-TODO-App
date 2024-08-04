package org.bugrahan.todoapp.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
public class TodoErrorWithTodoName extends BaseTodoError{
    @Accessors(prefix = "m_")
    private String m_todoName;

    public TodoErrorWithTodoName(String todoName, String errorMessage, int status)
    {
        super(errorMessage, status);
        m_todoName = todoName;
    }
}
