package com.example.testtask.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_counter")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer id;

    @Column(name = "username", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String username;

    @Column(name = "counter")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long counter;

    @Column(name = "lastmsg")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String message;

    public User(String username, Long counter, String lastmsg) {
        this.username = username;
        this.counter = counter;
        this.message = lastmsg;
    }
}
