package com.readlearncode.dukesbookshop.restclient;

import com.readlearncode.dukesbookshop.domain.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@ApplicationScoped
public class BookServiceImpl implements BookService {

    private static final String API_URL = "http://localhost:8081/rest-server"; //target URIs for the client
    private static final String BOOKS_ENDPOINT = API_URL + "/api/books";

    private List<Book> cachedBooks = new ArrayList<>(); //stores results

    private Client client;

    @PostConstruct
    public void initialise() {
        //initialize consumer client
        client = ClientBuilder.newClient(); 
    }

    @Override
    public List<Book> getBooks() {
        //bind target URI to client
        WebTarget target = client.target(BOOKS_ENDPOINT);
        
        //set MediaType and method GET
        Response response = target.request(MediaType.APPLICATION_JSON).get(); 

        /*Code to return to Java Objects
        ################################
        //response obj encapsulates a List<Book>.
        cachedBooks = response.readEntity(new GenericType<ArrayList<Book>>(){});
        
        return Collections.unmodifiableList(cachedBooks); //make the list immutable
        */
        
        cachedBooks.clear();
        JsonArray jsonArray = response.readEntity(JsonArray.class); //de-serialize List into JsonArray format.
        
        for(int i=0; i<jsonArray.size(); i++) {
            JsonObject bookJson = jsonArray.getJsonObject(i);
            List<LinkResource> hypermedia = extractLinks(bookJson.getJsonArray("links"));
            List<Author> authors = extractAuthors(bookJson.getJsonArray("authors"));
            
            Book book = new BookBuilder()
                    .setId(bookJson.getString("id"))
                    .setTitle(bookJson.getString("title"))
                    .setDescription(bookJson.getString("description"))
                    .setPrice((float) bookJson.getJsonNumber("price").doubleValue())
                    .setImageFileName(bookJson.getString("imageFileName"))
                    .setAuthors(authors)
                    .setPublished(bookJson.getString("published"))
                    .setLink(bookJson.getString("link"))
                    .setHyperlinks(hypermedia)
                    .createBook();
            
            cachedBooks.add(book);
        }
        return Collections.unmodifiableList(cachedBooks);
    }

    @PreDestroy
    private void destroy() {
        client.close();
    }


    @Override
    public Book getBook(String id) {
        return null;
    }

    //implement method to consume the "delete" HATEOAS endpoint.
    @Override
    public void deleteBook(String isbn) {

        for(Book book:cachedBooks) {
            if(book.getId().equals(isbn)) {
                for(LinkResource linkResource:book.getLinks()) {
                    if(linkResource.getRel().equals("delete")) {
                        String uri = linkResource.getUri();
                        client.target(uri).request(MediaType.APPLICATION_JSON).delete();
                        break; //No need to continue if isbn matches.
                    }
                }
            }
        }
    }

    @Override
    public Book saveBook(Book book) {
        return null;
    }

    @Override
    public List<LinkResource> extractLinks(JsonArray linkArray) {
        
        List<LinkResource> links = new ArrayList<>();
        
        for(int j=0; j<linkArray.size(); j++) {
            JsonObject jsonObject = linkArray.getJsonObject(j);
            String rel = jsonObject.getString("rel", "");
            String type = jsonObject.getString("type", "");
            String uri = jsonObject.getString("uri", "");
            
            links.add(new LinkResource(rel, type, uri));
        }
        
        return Collections.unmodifiableList(links);
    }
    
    @Override
    public List<Author> extractAuthors(JsonArray authorArray) {

        List<Author> authors = new ArrayList<>();

        for (int j = 0; j < authorArray.size(); j++) {
            JsonObject jObject = authorArray.getJsonObject(j);
            String id = jObject.getString("id", "");
            String firstName = jObject.getString("firstName", "");
            String lastName = jObject.getString("lastName", "");
            String blogURL = jObject.getString("blogURL", "");

            authors.add(new Author(id, firstName, lastName, blogURL));
        }

        return Collections.unmodifiableList(authors);
    }


}
