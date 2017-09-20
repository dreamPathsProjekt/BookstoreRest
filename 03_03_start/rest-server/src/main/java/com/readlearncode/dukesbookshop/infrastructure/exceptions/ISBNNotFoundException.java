
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

/**
 *
 * @author i.dritsas
 */
public class ISBNNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ISBNNotFoundException</code> without
     * detail message.
     */
    public ISBNNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ISBNNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ISBNNotFoundException(String msg) {
        super(msg);
    }
}
