package com.example.libros.application.services;

import com.example.libros.api.dtos.LibroDto;
import com.example.libros.api.mappers.LibroMapper;
import com.example.libros.domain.exceptions.LibroNotFoundException;
import com.example.libros.domain.models.Libro;
import com.example.libros.infrastructure.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    //Declaro instancia del repositorio
    @Autowired
    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    // Genero los metodos del CRUD
    public List<LibroDto> getLibros(){
        List<Libro> libros = libroRepository.findAll();
        return libros.stream()
                .map(LibroMapper::libroToDtoMap)
                .toList();

    }
    //Traer Libro por ID
    public LibroDto getLibroById(Long id){
        return LibroMapper.libroToDtoMap(libroRepository.findById(id).get());
    }
    //Crear un libro
    public LibroDto createLibro(LibroDto libro){
        return LibroMapper.libroToDtoMap(libroRepository.save(LibroMapper.dtoToLibro(libro)));
    }

    public LibroDto updateLibro(Long id,LibroDto libro){
        Optional<Libro> libroCreated = libroRepository.findById(id);
        if(libroCreated.isPresent()){
            Libro entity = libroCreated.get();
            // Actualizo los campos del usuario con los valores del UserDto
            Libro libroUpdate = LibroMapper.dtoToLibro(libro);

            libroUpdate.setId(entity.getId());

            Libro saved = libroRepository.save(libroUpdate);
            return LibroMapper.libroToDtoMap(saved);
        }else {
            throw new LibroNotFoundException("Book not found with id: " + id);
        }
    }

    public String deleteLibro(Long id){
        if(libroRepository.existsById(id)){
            libroRepository.deleteById(id);
            return "Se elimino el libro exitosamente";
        }else {
            return "El libro no se elimino";
        }
    }

}
