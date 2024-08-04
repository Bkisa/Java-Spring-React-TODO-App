package org.bugrahan.todoapp.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
public class TodoErrorWithTodoId extends BaseTodoError{
    @Accessors(prefix = "m_")
    private int m_todoId;


    public TodoErrorWithTodoId(int todoId, String errorMessage, int status)
    {
        super(errorMessage, status);
        m_todoId = todoId;
    }
}
