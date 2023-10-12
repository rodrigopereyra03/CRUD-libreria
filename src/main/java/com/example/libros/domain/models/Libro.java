package com.example.libros.domain.models;




import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_libro")
    private String name;
    private String autor;
    private LocalDate fechaDeEdicion;

    public Libro(String name, String autor, LocalDate fechaDeEdicion) {
        this.name = name;
        this.autor = autor;
        this.fechaDeEdicion = fechaDeEdicion;
    }

    public Libro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaDeEdicion() {
        return fechaDeEdicion;
    }

    public void setFechaDeEdicion(LocalDate fechaDeEdicion) {
        this.fechaDeEdicion = fechaDeEdicion;
    }
}
