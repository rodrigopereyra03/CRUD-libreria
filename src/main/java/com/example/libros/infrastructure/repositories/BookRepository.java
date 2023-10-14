package com.example.libros.infrastructure.repositories;


import com.example.libros.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "SELECT l FROM Book l WHERE l.name LIKE :keyword%" + "OR l.author LIKE :keyword%")
    List<Book> search(@Param("keyword") String keyword);
}
