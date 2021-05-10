package br.com.rollo.rafael.casadocodigoapi.domain.books;

import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface BookRepository extends Repository<Book, Integer> {

    Book save(Book book);

    List<Book> findAll();

    void deleteById(Integer id);

    Book findById(Integer id);
}
