package com.example.libros.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookDto {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
   // @NotBlank(message = "author is required")

    private LocalDate editionDate;

    private String codeBook;

    private AuthorDto authorDto;
}
