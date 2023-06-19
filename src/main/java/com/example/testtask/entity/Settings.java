package com.example.testtask.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "parameter", nullable = false)
    private Long delay;
}
