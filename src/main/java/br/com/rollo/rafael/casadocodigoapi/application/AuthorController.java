package br.com.rollo.rafael.casadocodigoapi.application;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.rollo.rafael.casadocodigoapi.application.output.BookOutput;
import br.com.rollo.rafael.casadocodigoapi.domain.books.Book;
import br.com.rollo.rafael.casadocodigoapi.domain.books.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rollo.rafael.casadocodigoapi.application.input.AuthorInput;
import br.com.rollo.rafael.casadocodigoapi.application.output.AuthorOutput;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.AuthorRepository;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.AuthorUpdate;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private AuthorRepository authors;
    private AuthorUpdate authorUpdate;
    private BookRepository books;

    @Autowired
    public AuthorController(AuthorRepository authors, AuthorUpdate authorUpdate, BookRepository books) {
        this.authors = authors;
        this.authorUpdate = authorUpdate;
        this.books = books;
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorOutput> create(@Valid @RequestBody AuthorInput input, UriComponentsBuilder uriBuilder) {
        Author createdAuthor = this.authors.save(input.toAuthor());

        URI authorPath = uriBuilder
                .path("/api/author/{id}")
                .buildAndExpand(createdAuthor.getId())
                .toUri();

        return ResponseEntity.created(authorPath).body(AuthorOutput.buildFrom(createdAuthor));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorOutput>> list() {
        List<Author> foundAuthors = this.authors.findAll();
        return ResponseEntity.ok().body(AuthorOutput.listFrom(foundAuthors));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer authorId) {
        authors.deleteById(authorId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorOutput> update(@PathVariable("id") Integer authorId,
                                               @Valid @RequestBody AuthorInput input) {
        Author author = input.toAuthor();

        Author updatedAuthor = authorUpdate.execute(authorId, author);
        return ResponseEntity.ok().body(AuthorOutput.buildFrom(updatedAuthor));
    }

    @GetMapping(value = "/{id}/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookOutput>> listBooksOfAuthor(@PathVariable("id") Integer authorId) {
        List<Book> foundBooks = this.books.findByAuthorId(authorId);
        return ResponseEntity.ok().body(BookOutput.listFrom(foundBooks));
    }
}
