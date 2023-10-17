package com.example.libros.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
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


    public Book(String name, LocalDate editionDate, String codeBook, Author author) {
        this.name = name;
        this.editionDate = editionDate;
        this.codeBook = codeBook;
        this.author = author;
    }

    public Book() {

    }

    public Book(Long l, String libro1) {
    }
}
