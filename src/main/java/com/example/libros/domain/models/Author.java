package com.example.libros.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Long id;

    private String name_author;


    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    @Column(name = "id_books")
    private List<Book> books;




    public Author( String name_author) {
        this.name_author = name_author;
    }

    public Author() {}

    public Author(Long l, String author1) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }
}
