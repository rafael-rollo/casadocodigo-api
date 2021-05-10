package br.com.rollo.rafael.casadocodigoapi.domain.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookUpdate {

    private BookRepository books;

    @Autowired
    public BookUpdate(BookRepository books) {
        this.books = books;
    }

    public Book execute(Integer bookId, Book bookUpdate) {
        Book foundBook = books.findById(bookId);
        foundBook.updateBy(bookUpdate);

        return foundBook;
    }
}
