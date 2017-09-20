/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author i.dritsas
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        
        //Return stream of concatenated violations.
        final String message = exception.getConstraintViolations().stream()
                .map((violation) -> extractPropertyName(violation.getPropertyPath().toString())
                + " : " + violation.getMessage())
                .collect(Collectors.joining(", "));
        
        //You can choose to put message in header and/or Response body.
        return Response.status(Status.BAD_REQUEST).header("X-Validation-Failure", message).build();
                
    }
    //private helper method.
    private String extractPropertyName(String path) {
        return path.substring(path.lastIndexOf(".") + 1); //extract values from property name.
    }
    
}
