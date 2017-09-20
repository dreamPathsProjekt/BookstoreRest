package com.readlearncode.dukesbookshop.infrastructure;

import com.readlearncode.dukesbookshop.domain.Author;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Stateless;

/**
 *
 * @author i.dritsas
 */
@Stateless
public class AuthorRepositoryBean implements AuthorRepository {

    private final Map<String, Author> authors = new HashMap<>();

    @Override
    public List<Author> saveAuthors(List<Author> authors) {
        System.out.println(authors);

        authors.forEach(this::saveAuthor);
        return authors;

    }

    @Override
    public Author saveAuthor(Author author) {
        System.out.println(author);

        authors.put(author.getId(), author);
        return author;
    }

    @Override
    public Author deleteAuthor(String id) {
        return authors.remove(id);
    }

    @Override
    public List<Author> getAll() {
        return authors.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Author> getById(String id) {
        return Optional.ofNullable(authors.get(id));
    }

}
