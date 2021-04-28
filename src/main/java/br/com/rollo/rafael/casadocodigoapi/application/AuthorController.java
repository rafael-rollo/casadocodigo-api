package br.com.rollo.rafael.casadocodigoapi.application;

import br.com.rollo.rafael.casadocodigoapi.application.input.AuthorInput;
import br.com.rollo.rafael.casadocodigoapi.application.output.AuthorOutput;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.Author;
import br.com.rollo.rafael.casadocodigoapi.domain.authors.AuthorRepository;
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
@RequestMapping("/api/author")
public class AuthorController {

    private AuthorRepository authors;

    @Autowired
    public AuthorController(AuthorRepository authors) {
        this.authors = authors;
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorOutput> create(@Valid @RequestBody AuthorInput input, UriComponentsBuilder uriBuilder) {
        Author createdAuthor = this.authors.save(input.toAuthor());

        URI authorPath = uriBuilder
                .path("/api/author/{id}")
                .buildAndExpand(createdAuthor.getId())
                .toUri();

        return ResponseEntity.created(authorPath).body(AuthorOutput.buildFrom(createdAuthor));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorOutput>> list() {
        List<Author> foundAuthors = this.authors.findAll();
        return ResponseEntity.ok().body(AuthorOutput.listFrom(foundAuthors));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer authorId) {
        authors.deleteById(authorId);
        return ResponseEntity.noContent().build();
    }
}
