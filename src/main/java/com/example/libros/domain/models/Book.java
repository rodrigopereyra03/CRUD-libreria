package com.example.libros.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "edition_date")
    private LocalDate editionDate;
    @Column(name = "code_book")
    private String codeBook;

    @ManyToOne
    private Author author;


}
