package io.whitfin.dottie.exception;

/**
 * Exception class to be raised on all errors parsing notation.
 */
public class NotationException extends IllegalArgumentException {

    /**
     * Creates a new exception from a raw message.
     *
     * @param message
     *      the {@link String} message to create from.
     */
    public NotationException(String message) {
        super(message);
    }

    /**
     * Creates a new exception using positional arguments.
     *
     * @param key
     *      the key being parsed currently.
     * @param current
     *      the current character being parsed.
     * @param index
     *      the the current position of the parser.
     */
    public NotationException(String key, char current, int index) {
        this("Unable to parse '" + key + "' at character '" + current + "', column " + (index + 1) + "!");
    }
}