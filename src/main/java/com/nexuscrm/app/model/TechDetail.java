package com.nexuscrm.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "techDetail")
public class TechDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    private String editorial;

    private Double peso;

    @OneToOne(mappedBy = "techDetail")
    private Book book;
}
