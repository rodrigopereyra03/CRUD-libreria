package com.example.libros.infrastructure.repositories.sql;

import com.example.libros.domain.exceptions.BookNotFoundException;
import com.example.libros.domain.models.Book;
import com.example.libros.infrastructure.repositories.IBookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookSQLRepository implements IBookRepository {

    private final IBookSQLRepository sqlRepository;

    public BookSQLRepository(IBookSQLRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }

    @Override
    public List<Book> findAll() {
        return sqlRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> book = sqlRepository.findById(id);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found with id: " + id);

        }
        return book.get();
    }

    @Override
    public Book save(Book book) {
        return sqlRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        sqlRepository.deleteById(id);
    }

    @Override
    public List<Book> search(String keyword) {
        return sqlRepository.search(keyword);
    }

    @Override
    public Boolean existsById(Long id) {
        return sqlRepository.existsById(id);
    }

    @Override
    public List<Book> findByCode(String code) {
        return sqlRepository.getBookByCode(code);
    }

    @Override
    public List<Book> findAllById(List<Long> idBooks) {
        return sqlRepository.findAllById(idBooks);
    }


}
