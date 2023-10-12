package com.example.libros.api.mappers;

import com.example.libros.api.dtos.LibroDto;
import com.example.libros.domain.models.Libro;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LibroMapper {
    //Los mappers me permiten enviar los datos desde una entidad hacia un dto o visceversa

    //Recibe un dto y devuelve un libro
    public Libro dtoToLibro(LibroDto dto){
        Libro libro = new Libro();
        libro.setName(dto.getName());
        libro.setAutor(dto.getAutor());
        libro.setFechaDeEdicion(dto.getFechaDeEdicion());
        libro.setId(dto.getId());

        return libro;
    }

    //Recibe un libro y devuelve un dto
    public LibroDto libroToDtoMap(Libro libro){
        LibroDto dto = new LibroDto();
        dto.setName(libro.getName());
        dto.setAutor(libro.getAutor());
        dto.setFechaDeEdicion(libro.getFechaDeEdicion());
        dto.setId(libro.getId());

        return dto;
    }


}
