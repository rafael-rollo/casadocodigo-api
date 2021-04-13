package br.com.rollo.rafael.casadocodigoapi.domain.books;

import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface BookRepository extends Repository<Book, Integer> {

    Book save(Book book);

}
