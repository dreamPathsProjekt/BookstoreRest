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
public class AuthorsExceptionNotFoundManager implements ExceptionMapper<AuthorsNotFoundException>{

    @Override
    public Response toResponse(AuthorsNotFoundException exception) {
        return Response.noContent().build();
    }
    
}
