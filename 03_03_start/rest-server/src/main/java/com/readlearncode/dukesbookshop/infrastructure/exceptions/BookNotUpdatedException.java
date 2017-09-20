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
public class BookNotUpdatedException extends Exception {

    /**
     * Creates a new instance of <code>BookNotUpdatedException</code> without
     * detail message.
     */
    public BookNotUpdatedException() {
    }

    /**
     * Constructs an instance of <code>BookNotUpdatedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BookNotUpdatedException(String msg) {
        super(msg);
    }
}
