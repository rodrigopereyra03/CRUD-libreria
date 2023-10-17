package com.example.libros.infrastructure.repositories.sql;


import com.example.libros.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookSQLRepository extends JpaRepository<Book,Long> {
    @Query(value = "SELECT l FROM Book l inner join Author a on l.author.id = a.id WHERE lower(a.name_author) LIKE :keyword%" + "OR lower(l.name) LIKE :keyword%")
    List<Book> search(@Param("keyword") String keyword);

    @Query(value = "SELECT l from Book l WHERE l.codeBook LIKE :code")
    List<Book> getBookByCode(@Param("code")String code);



}
