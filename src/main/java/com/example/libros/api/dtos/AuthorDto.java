package com.example.libros.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthorDto {

    private Long id;
    @NotBlank(message = "Author is required")
    private String name_author;

    private List<Long> idBooks;
}
