package br.com.rollo.rafael.casadocodigoapi.domain.authors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorUpdate {

    private AuthorRepository authors;

    @Autowired
    public AuthorUpdate(AuthorRepository authors) {
        this.authors = authors;
    }

    public Author execute(Integer authorId, Author authorUpdate) {
        Author foundAuthor = authors.findById(authorId);
        foundAuthor.updateBy(authorUpdate);

        return foundAuthor;
    }
}
