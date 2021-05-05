package br.com.rollo.rafael.casadocodigoapi.application.output;

import br.com.rollo.rafael.casadocodigoapi.domain.books.Book;
import br.com.rollo.rafael.casadocodigoapi.domain.books.Price;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class BookOutput {

    private Integer id;
    private String title;
    private String subtitle;
    private String coverImagePath;
    private List<Price> prices = new ArrayList<>();
    private String description;
    private AuthorOutput author;
    private Integer numberOfPages;
    private String isbn;
    private LocalDate publicationDate;

    private BookOutput(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.subtitle = book.getSubtitle();
        this.coverImagePath = book.getCoverImagePath();
        this.prices = book.getPrices();
        this.description = book.getDescription();
        this.author = AuthorOutput.buildFrom(book.getAuthor());
        this.numberOfPages = book.getNumberOfPages();
        this.isbn = book.getIsbn();
        this.publicationDate = book.getPublicationDate();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public String getDescription() {
        return description;
    }

    public AuthorOutput getAuthor() {
        return author;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublicationDate() {
        return this.publicationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static BookOutput buildFrom(Book book) {
        return new BookOutput(book);
    }

    public static List<BookOutput> listFrom(List<Book> books) {
        return books.stream()
                .map(BookOutput::new)
                .collect(toList());
    }
}
