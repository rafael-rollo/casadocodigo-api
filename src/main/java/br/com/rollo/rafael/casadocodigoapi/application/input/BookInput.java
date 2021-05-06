package br.com.rollo.rafael.casadocodigoapi.application.input;

import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.AuthorRepository;
import br.com.rollo.rafael.casadocodigoapi.domain.books.Book;
import br.com.rollo.rafael.casadocodigoapi.domain.books.BookType;
import br.com.rollo.rafael.casadocodigoapi.domain.books.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static java.time.format.DateTimeFormatter.*;

public class BookInput {

    private static final String DUMMY_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus.";

    @NotBlank
    private String title;

    @NotBlank
    private String subtitle;

    @NotBlank
    private String coverImagePath;

    @NotNull
    @DecimalMin("10.00")
    @Digits(integer = 2,fraction=2)
    private BigDecimal eBookPrice;

    @NotNull
    @DecimalMin("10.00")
    @Digits(integer = 2,fraction=2)
    private BigDecimal hardcoverPrice;

    @NotNull
    @DecimalMin("10.00")
    @Digits(integer = 2,fraction=2)
    private BigDecimal comboPrice;

    private String description;

    @NotNull
    @Min(1)
    private Integer authorId;

    @NotNull
    @Min(30)
    private Integer numberOfPages;

    @NotBlank
    private String isbn;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    public BigDecimal geteBookPrice() {
        return eBookPrice;
    }

    public void seteBookPrice(BigDecimal eBookPrice) {
        this.eBookPrice = eBookPrice;
    }

    public BigDecimal getHardcoverPrice() {
        return hardcoverPrice;
    }

    public void setHardcoverPrice(BigDecimal hardcoverPrice) {
        this.hardcoverPrice = hardcoverPrice;
    }

    public BigDecimal getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(BigDecimal comboPrice) {
        this.comboPrice = comboPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Book toBook(AuthorRepository authors) {
        Author author = authors.findById(this.authorId);

        List<Price> prices = Arrays.asList(
                new Price(BookType.EBOOK, this.eBookPrice),
                new Price(BookType.HARDCOVER, this.hardcoverPrice),
                new Price(BookType.COMBO, this.comboPrice));

        Book book = new Book(this.title, this.subtitle, this.coverImagePath,
                prices, author, this.numberOfPages,
                this.isbn, this.publicationDate);

        String description = this.description == null || this.description.isEmpty()
                ? BookInput.DUMMY_DESCRIPTION
                : this.description;
        book.setDescription(description);

        return book;
    }
}
