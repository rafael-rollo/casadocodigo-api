package br.com.rollo.rafael.casadocodigoapi.application.output;

import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class AuthorOutput {

    private Integer id;
    private String firstName;
    private String lastName;
    private String bio;
    private String profilePicturePath;
    private List<String> technologies = new ArrayList<>();
    private Long publishedBooks;

    private AuthorOutput(Author author) {
        this.id = author.getId();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.bio = author.getBio();
        this.profilePicturePath = author.getProfilePicturePath();
        this.technologies = author.getTechnologiesSHeWritesAbout();
        this.publishedBooks = author.getPublishedBooks().stream().count();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public Long getPublishedBooks() {
        return publishedBooks;
    }

    public static AuthorOutput buildFrom(Author author) {
        return new AuthorOutput(author);
    }

    public static List<AuthorOutput> listFrom(List<Author> authors) {
        return authors.stream()
                .map(AuthorOutput::new)
                .collect(toList());
    }
}
