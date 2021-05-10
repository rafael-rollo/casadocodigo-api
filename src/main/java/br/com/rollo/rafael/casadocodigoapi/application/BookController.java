package br.com.rollo.rafael.casadocodigoapi.application;

import br.com.rollo.rafael.casadocodigoapi.application.input.BookInput;
import br.com.rollo.rafael.casadocodigoapi.application.output.BookOutput;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.AuthorRepository;
import br.com.rollo.rafael.casadocodigoapi.domain.books.Book;
import br.com.rollo.rafael.casadocodigoapi.domain.books.BookCreation;
import br.com.rollo.rafael.casadocodigoapi.domain.books.BookRepository;
import br.com.rollo.rafael.casadocodigoapi.domain.books.BookUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private AuthorRepository authors;
    private BookCreation bookCreation;
    private BookUpdate bookUpdate;
    private BookRepository books;

    @Autowired
    public BookController(AuthorRepository authors, BookCreation bookCreation,
                          BookUpdate bookUpdate, BookRepository books) {
        this.authors = authors;
        this.bookCreation = bookCreation;
        this.bookUpdate = bookUpdate;
        this.books = books;
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookOutput> create(@Valid @RequestBody BookInput input, UriComponentsBuilder uriBuilder) {
        Book book = input.toBook(this.authors);
        Book createdBook = this.bookCreation.execute(book);

        URI bookPath = uriBuilder
                .path("/api/book/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();

        return ResponseEntity
                .created(bookPath)
                .body(BookOutput.buildFrom(createdBook));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookOutput>> list() {
        List<Book> foundBooks = this.books.findAll();
        return ResponseEntity.ok().body(BookOutput.listFrom(foundBooks));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer bookId) {
        books.deleteById(bookId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookOutput> update(@PathVariable("id") Integer bookId,
                                             @Valid @RequestBody BookInput input) {
        Book book = input.toBook(this.authors);

        Book updatedBook = bookUpdate.execute(bookId, book);
        return ResponseEntity.ok().body(BookOutput.buildFrom(updatedBook));
    }
}
