package com.example.libros.api.dtos;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LibroDto {
    private Long id;
    private String name;
    private String autor;
    private LocalDate fechaDeEdicion;
}
