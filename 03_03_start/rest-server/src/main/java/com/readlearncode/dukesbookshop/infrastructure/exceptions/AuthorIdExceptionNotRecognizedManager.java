
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author i.dritsas
 */
@Provider
public class AuthorIdExceptionNotRecognizedManager implements ExceptionMapper<AuthorIdNotRecognizedException>{

    @Override
    public Response toResponse(AuthorIdNotRecognizedException exception) {
        return Response.noContent().build();
    }
    
}
