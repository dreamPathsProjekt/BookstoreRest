
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author i.dritsas
 */
@Provider
public class ISBNExceptionNotFoundManager implements ExceptionMapper<ISBNNotFoundException>{
    
    @Override
    public Response toResponse(ISBNNotFoundException exception) {
        return Response.noContent().build();
    }
    
}
