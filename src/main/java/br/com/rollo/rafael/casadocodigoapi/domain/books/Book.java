package br.com.rollo.rafael.casadocodigoapi.domain.books;

import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String subtitle;

    @NotBlank
    private String coverImagePath;

    @ElementCollection
    @NotEmpty
    @Valid
    private List<Price> prices = new ArrayList<>();

    @Lob
    @Column(length=512)
    private String description;

    @NotNull
    @ManyToOne
    @JsonManagedReference
    private Author author;

    @NotNull
    @Min(30)
    private Integer numberOfPages;

    @NotBlank
    private String isbn;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate publicationDate;

    @Deprecated
    public Book() {}

    public Book(String title, String subtitle, String coverImagePath,
                List<Price> prices, Author author, Integer numberOfPages,
                String isbn, LocalDate publicationDate) {
        this.title = title;
        this.subtitle = subtitle;
        this.coverImagePath = coverImagePath;
        this.prices = prices;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }

    public Integer getId() {
        return this.id;
    }

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

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
}
