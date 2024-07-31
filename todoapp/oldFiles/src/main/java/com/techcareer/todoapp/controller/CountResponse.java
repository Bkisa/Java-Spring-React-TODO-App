package com.techcareer.todoapp.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountResponse {
    @Accessors(prefix = "m_")
    private long m_count;
}
