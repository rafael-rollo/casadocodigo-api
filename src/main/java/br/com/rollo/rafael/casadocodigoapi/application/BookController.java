package br.com.rollo.rafael.casadocodigoapi.application;

import br.com.rollo.rafael.casadocodigoapi.application.input.BookInput;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.AuthorRepository;
import br.com.rollo.rafael.casadocodigoapi.domain.books.Book;
import br.com.rollo.rafael.casadocodigoapi.domain.books.BookCreation;
import br.com.rollo.rafael.casadocodigoapi.domain.books.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private AuthorRepository authors;
    private BookCreation bookCreation;

    @Autowired
    public BookController(AuthorRepository authors, BookCreation bookCreation) {
        this.authors = authors;
        this.bookCreation = bookCreation;
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> create(@Valid @RequestBody BookInput input, UriComponentsBuilder uriBuilder) {
        Book book = input.toBook(this.authors);
        Book createdBook = this.bookCreation.execute(book);

        URI bookPath = uriBuilder
                .path("/api/book/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();

        return ResponseEntity.created(bookPath).body(createdBook);
    }
}