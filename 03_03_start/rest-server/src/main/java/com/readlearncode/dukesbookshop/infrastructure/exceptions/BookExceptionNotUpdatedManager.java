/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author i.dritsas
 */
@Provider
public class BookExceptionNotUpdatedManager implements ExceptionMapper<BookNotUpdatedException>{

    @Override
    public Response toResponse(BookNotUpdatedException exception) {
        return Response.notModified().build();
    }
    
}
