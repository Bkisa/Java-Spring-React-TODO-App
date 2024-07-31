package com.techcareer.todoapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue
    @Accessors(prefix = "m_")
    private long m_id;

    @NotEmpty(message = "Title is required")
    @Accessors(prefix = "m_")
    private String m_title;

    @NotNull(message = "Target date is required")
    @Accessors(prefix = "m_")
    private LocalDate m_targetDate;

    @Accessors(prefix = "m_")
    private String m_username;

    @Accessors(prefix = "m_")
    private boolean m_isCompleted;


    public Todo(String title, LocalDate targetDate, String username) {
        super();
        m_title = title;
        m_targetDate = targetDate;
        m_username = username;
        m_isCompleted = false;
    }

    public boolean getIsCompleted()
    {
        return m_isCompleted;
    }

    public void setIsCompleted(boolean isCompleted)
    {
        this.m_isCompleted = isCompleted;
    }
}
