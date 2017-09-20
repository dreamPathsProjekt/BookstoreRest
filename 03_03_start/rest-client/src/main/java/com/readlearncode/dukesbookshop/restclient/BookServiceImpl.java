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
        
        //response obj encapsulates a List<Book>.
        cachedBooks = response.readEntity(new GenericType<ArrayList<Book>>(){});
        return cachedBooks;
    }

    @PreDestroy
    private void destroy() {
        client.close();
    }


    @Override
    public Book getBook(String id) {
        return null;
    }

    @Override
    public void deleteBook(String isbn) {

    }

    @Override
    public Book saveBook(Book book) {
        return null;
    }

    @Override
    public List<Author> extractAuthors(JsonArray authorArray) {
        return null;
    }


}
