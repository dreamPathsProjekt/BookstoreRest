
package com.readlearncode.dukesbookshop.infrastructure;



import java.util.List;
import com.readlearncode.dukesbookshop.domain.Author;
import java.util.Optional;
/**
 *
 * @author i.dritsas
 */
public interface AuthorRepository {
    
    public Author saveAuthor(Author author);
    
    public List<Author> saveAuthors(List<Author> authors);
    
    public Author deleteAuthor(String id);
    
    public List<Author> getAll();
    
    public Optional<Author> getById(String id);
}
