package com.amaghrabi.Security.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "error_codes")
@Data
public class AppError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private Integer code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;
}
