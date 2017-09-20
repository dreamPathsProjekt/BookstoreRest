
package com.readlearncode.dukesbookshop.rest;

import com.readlearncode.dukesbookshop.domain.Author;
import com.readlearncode.dukesbookshop.infrastructure.AuthorRepository;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
@Path("/authors")
public class AuthorResource {
 
    @EJB
    private AuthorRepository authorRepository;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthors() {
        List<Author> authors = authorRepository.getAll();
        GenericEntity<List<Author>> authorsWrapper = new GenericEntity<List<Author>>(authors){};
        
        return Response.ok(authorsWrapper).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getAuthor(final @PathParam("id") String id) {
        Optional<Author>  author = authorRepository.getById(id);
        
        if(author.isPresent()) {
           return Response.ok(author.get()).build(); 
        }
        return Response.noContent().build();
    }
}
