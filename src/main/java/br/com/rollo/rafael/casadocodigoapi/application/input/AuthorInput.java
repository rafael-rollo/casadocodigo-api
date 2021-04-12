package br.com.rollo.rafael.casadocodigoapi.application.input;

import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;
import javax.validation.constraints.NotBlank;

public class AuthorInput {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String bio;

    @NotBlank
    private String profilePicturePath;

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

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public Author toAuthor() {
        return new Author(this.firstName, this.lastName, this.bio, this.profilePicturePath);
    }
}
