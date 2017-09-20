
package com.readlearncode.dukesbookshop.infrastructure.exceptions;

/**
 *
 * @author i.dritsas
 */
public class AuthorIdNotRecognizedException extends Exception {

    /**
     * Creates a new instance of <code>AuthorIdNotRecognized</code> without
     * detail message.
     */
    public AuthorIdNotRecognizedException() {
    }

    /**
     * Constructs an instance of <code>AuthorIdNotRecognized</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AuthorIdNotRecognizedException(String msg) {
        super(msg);
    }
}
