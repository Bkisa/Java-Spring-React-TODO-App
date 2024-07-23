package com.techcareer.todoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_table")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Accessors(prefix = "m_")
    private int m_id;

    @Accessors(prefix = "m_")
    private  String m_name;

    @Accessors(prefix = "m_")
    private String m_status;

    @Column
    @Accessors(prefix = "m_")
    private String m_username;
}
