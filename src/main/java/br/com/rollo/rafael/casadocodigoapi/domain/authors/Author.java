package br.com.rollo.rafael.casadocodigoapi.domain.authors;

import br.com.rollo.rafael.casadocodigoapi.domain.books.Book;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String bio;

    @NotBlank
    private String profilePicturePath;

    @ElementCollection
    private List<String> technologiesSHeWritesAbout = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Book> publishedBooks = new ArrayList<>();

    @Deprecated
    public Author() { }

    public Author(String firstName, String lastName, String bio, String profilePicturePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profilePicturePath = profilePicturePath;
    }

    public Integer getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getTechnologiesSHeWritesAbout() {
        return technologiesSHeWritesAbout;
    }

    public void setTechnologiesSHeWritesAbout(List<String> technologiesSHeWritesAbout) {
        this.technologiesSHeWritesAbout = technologiesSHeWritesAbout;
    }

    public List<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<Book> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }
}
