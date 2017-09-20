
package com.readlearncode.dukesbookshop.rest;

import com.readlearncode.dukesbookshop.domain.Author;
import com.readlearncode.dukesbookshop.domain.Book;
import com.readlearncode.dukesbookshop.infrastructure.BookRepository;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author i.dritsas
 */
@Stateless
@Path("/books")
public class BookResource {
    
    @EJB
    BookRepository bookRepository;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        
        List<Book> books = bookRepository.getAll();
        GenericEntity<List<Book>> bookWrapper = new GenericEntity<List<Book>>(books){};

        return Response.status(Response.Status.OK).entity(bookWrapper).build();  //also Response.ok(bookWrapper).build()
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveBook(final Book book) {
        
        Book persistedBook = bookRepository.saveBook(book);
        return Response.ok(persistedBook).build(); //We should use Response.created(URI) for 201 Headers on Create/POST
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn: \\d{9}[\\d|X]$}") //variable isbn matches with varname isbn in PathParam
    public Response getBookByIsbn(final @PathParam("isbn") String isbn) {
        Optional<Book> book = bookRepository.getByIsbn(isbn);
        //usage of Optional methods and book.get() retrieves the wrapped Book object
        if(book.isPresent()) {
            return Response.ok(book.get()).build();
        }
        return Response.noContent().build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{isbn: \\d{9}[\\d|X]$}") 
    public Response updateBook(final @PathParam("isbn") String isbn, final Book book) {
        Optional<Book> bookToUpdate = bookRepository.getByIsbn(isbn);
        
        if(bookToUpdate.isPresent() && bookToUpdate.get().getId().equals(book.getId())) {
            Book persistedBook = bookRepository.saveBook(book);
            return Response.ok(persistedBook).build();
        }
        return Response.notModified().build();
        
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn: \\d{9}[\\d|X]$}/authors")
    public Response getAuthorsForBook(final @PathParam("isbn") String isbn) {
        List<Author> authors = bookRepository.getByIsbn(isbn).get().getAuthors();
        GenericEntity<List<Author>> authorsWrapper = new GenericEntity<List<Author>>(authors){};
        
        return Response.ok(authorsWrapper).build();
    }
}
