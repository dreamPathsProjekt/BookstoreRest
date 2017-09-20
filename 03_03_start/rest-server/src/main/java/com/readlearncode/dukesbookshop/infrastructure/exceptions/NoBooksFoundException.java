/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

/**
 *
 * @author i.dritsas
 */
public class NoBooksFoundException extends Exception {

    /**
     * Creates a new instance of <code>NoBooksFoundException</code> without
     * detail message.
     */
    public NoBooksFoundException() {
    }

    /**
     * Constructs an instance of <code>NoBooksFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoBooksFoundException(String msg) {
        super(msg);
    }
}
