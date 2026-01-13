package com.nexuscrm.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "user") // Define el nombre de la tabla en la DB
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @JsonProperty("last_name")
    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(unique = true)
    @JsonProperty("email") // Fuerza a que Bruno use este nombre
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;

    // --- Constructores ---

    public User() {
        // Requerido por JPA
    }

    public User(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }


}