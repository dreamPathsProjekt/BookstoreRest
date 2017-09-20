
package com.readlearncode.dukesbookshop.infrastructure;

import com.readlearncode.dukesbookshop.domain.Book;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author i.dritsas
 */
public interface BookRepository {
   public Book saveBook(final Book book); 
   
   public Optional<Book> deleteBook(final String id);
   
   public List<Book> getAll();
   
   public Optional<Book> getByIsbn(final String id);
}
