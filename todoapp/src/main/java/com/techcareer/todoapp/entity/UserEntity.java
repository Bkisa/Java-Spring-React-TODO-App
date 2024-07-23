package com.techcareer.todoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Accessors(prefix = "m_")
    private int m_id;

    @Column(name = "username", unique = true)
    @Accessors(prefix = "m_")
    private String m_username;
    @Accessors(prefix = "m_")
    private String m_password;
}
