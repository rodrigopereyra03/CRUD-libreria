package com.example.libros.infrastructure.repositories.sql;

import com.example.libros.domain.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorSQLRepository extends JpaRepository<Author,Long> {

}
