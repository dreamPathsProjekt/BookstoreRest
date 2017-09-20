
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author i.dritsas
 */
@Provider
public class NoBooksFoundExceptionManager implements ExceptionMapper<NoBooksFoundException>{

    @Override
    public Response toResponse(NoBooksFoundException exception) {
        return Response.noContent().build();
    }
    
}
