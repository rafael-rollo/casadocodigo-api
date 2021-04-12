package br.com.rollo.rafael.casadocodigoapi.domain.authors;

import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AuthorRepository extends Repository<Author, Integer> {

    Author save(Author author);

    List<Author> findAll();

}
