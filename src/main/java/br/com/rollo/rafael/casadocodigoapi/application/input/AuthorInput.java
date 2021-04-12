package br.com.rollo.rafael.casadocodigoapi.application.input;

import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;
import br.com.rollo.rafael.casadocodigoapi.domain.books.Book;

import javax.validation.constraints.NotBlank;

public class AuthorInput {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String bio;

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

    public Author toAuthor() {
        return new Author(this.firstName, this.lastName, this.bio);
    }
}
