package br.com.rollo.rafael.casadocodigoapi.domain.books;

import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCreation {

    private BookRepository books;

    @Autowired
    public BookCreation(BookRepository books) {
        this.books = books;
    }

    public Book execute(Book book) {
        Book createdBook = this.books.save(book);

        Author booksAuthor = createdBook.getAuthor();
        booksAuthor.addBook(createdBook);

        return createdBook;
    }
}
