/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readlearncode.dukesbookshop.infrastructure;

import com.readlearncode.dukesbookshop.domain.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author i.dritsas
 */
@Stateless
public class BookRepositoryBean implements BookRepository{

    @EJB
    private AuthorRepository authorRepository;
    
    private static final String IMAGE_LOCATION = "/images/covers/";

    private final Map<String, Book> books = new HashMap<>();
    
    @Override
    public Book saveBook(Book book) {
        if(book.getImageFileName().length() == 0){
            book.setImageFileName(IMAGE_LOCATION.concat("no_image.png"));
        }

        authorRepository.saveAuthors(book.getAuthors());
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> deleteBook(String id) {
        return Optional.of(books.remove(id));
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Optional<Book> getByIsbn(String id) {
        return Optional.ofNullable(books.get(id));
    }
    
}
