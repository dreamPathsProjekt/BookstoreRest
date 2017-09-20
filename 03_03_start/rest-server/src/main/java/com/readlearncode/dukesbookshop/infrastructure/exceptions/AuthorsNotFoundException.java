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
public class AuthorsNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>AuthorsNotFoundException</code> without
     * detail message.
     */
    public AuthorsNotFoundException() {
    }

    /**
     * Constructs an instance of <code>AuthorsNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AuthorsNotFoundException(String msg) {
        super(msg);
    }
}
