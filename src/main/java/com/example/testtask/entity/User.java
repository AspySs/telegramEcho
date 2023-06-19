package com.example.testtask.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


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

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "counter")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long counter;


    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    @Column(name = "lastmsg")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String username, Long counter, String lastmsg){
        this.username = username;
        this.counter = counter;
        this.message = lastmsg;
    }
}
