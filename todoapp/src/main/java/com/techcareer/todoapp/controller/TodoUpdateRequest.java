package com.techcareer.todoapp.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoUpdateRequest {
    @NotEmpty(message = "Title is required")
    @Accessors(prefix = "m_")
    private String m_title;

    @NotNull(message = "Target date is required")
    @Accessors(prefix = "m_")
    private LocalDate m_targetDate;
}
