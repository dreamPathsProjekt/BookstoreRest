package com.readlearncode.dukesbookshop.rest;

import com.readlearncode.dukesbookshop.domain.Author;
import com.readlearncode.dukesbookshop.domain.Book;
import com.readlearncode.dukesbookshop.domain.LinkResource;
import com.readlearncode.dukesbookshop.infrastructure.BookRepository;
import com.readlearncode.dukesbookshop.infrastructure.exceptions.AuthorsNotFoundException;
import com.readlearncode.dukesbookshop.infrastructure.exceptions.BookNotUpdatedException;
import com.readlearncode.dukesbookshop.infrastructure.exceptions.ISBNNotFoundException;
import com.readlearncode.dukesbookshop.infrastructure.exceptions.NoBooksFoundException;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author i.dritsas
 */
@Stateless
@Path("/books")
public class BookResource {

    //To retrive relative uri paths of resources
    @Context
    private UriInfo uriInfo;

    @EJB
    BookRepository bookRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() throws NoBooksFoundException {

        List<Book> books = bookRepository.getAll();
        if (!books.isEmpty()) {
            GenericEntity<List<Book>> bookWrapper = new GenericEntity<List<Book>>(books) {
            };

            return Response.status(Response.Status.OK).entity(bookWrapper).build();  //also Response.ok(bookWrapper).build()
        }
        
        throw new NoBooksFoundException();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveBook(@Valid final Book book) { //Validate input at the create or update method based on validation constraints on domain object.

        Book persistedBook = bookRepository.saveBook(book);
        return Response.ok(persistedBook).build(); //We should use Response.created(URI) for 201 Headers on Create/POST
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn: \\d{9}[\\d|X]$}") //variable isbn matches with varname isbn in PathParam
    public Response getBookByIsbn(final @PathParam("isbn") String isbn) throws ISBNNotFoundException {
        Optional<Book> book = bookRepository.getByIsbn(isbn);
        //usage of Optional methods and book.get() retrieves the wrapped Book object
        if (book.isPresent()) {

            Link self = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass()) //Resource class
                    .path(getClass(), "getBookByIsbn") //Resource method
                    .build(book.get().getId())) //Pass Isbn to link
                    .rel("self")
                    .type("GET")
                    .build();

            Link delete = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass()) //Resource class
                    .path(getClass(), "deleteBook") //Resource method
                    .build(book.get().getId())) //Pass Isbn to link
                    .rel("delete")
                    .type("DELETE")
                    .build();

            LinkResource selfLink = new LinkResource(self);
            LinkResource deleteLink = new LinkResource(delete);

            //Add linkResources to book object(JSON) we pass on the resource
            book.get().addLinkResource(selfLink);
            book.get().addLinkResource(deleteLink);

            //Also add links to the Http Header
            return Response.ok(book.get()).links(self, delete).build();
        }
        throw new ISBNNotFoundException();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{isbn: \\d{9}[\\d|X]$}")
    public Response updateBook(final @PathParam("isbn") String isbn, final Book book) throws BookNotUpdatedException, ISBNNotFoundException {
        Optional<Book> bookToUpdate = bookRepository.getByIsbn(isbn);

        if (bookToUpdate.isPresent() && bookToUpdate.get().getId().equals(book.getId())) {
            Book persistedBook = bookRepository.saveBook(book);
            return Response.ok(persistedBook).build();
        }
        throw new BookNotUpdatedException();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn: \\d{9}[\\d|X]$}/authors")
    public Response getAuthorsForBook(final @PathParam("isbn") String isbn) throws AuthorsNotFoundException, ISBNNotFoundException {
        List<Author> authors = bookRepository.getByIsbn(isbn).get().getAuthors();
        if (!authors.isEmpty()) {
            GenericEntity<List<Author>> authorsWrapper = new GenericEntity<List<Author>>(authors) {
            };
            return Response.ok(authorsWrapper).build();
        }
        throw new AuthorsNotFoundException();

    }

    @DELETE
    @Path("{isbn: \\d{9}[\\d|X]$}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(final @PathParam("isbn") String isbn) throws ISBNNotFoundException {
        return Response
                .ok(bookRepository.deleteBook(isbn).orElseThrow(ISBNNotFoundException::new))
                .build();
    }
}
