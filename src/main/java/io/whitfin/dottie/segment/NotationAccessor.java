package io.whitfin.dottie.segment;

import javax.annotation.Nonnull;

/**
 * Segment class to represent a notation accessor.
 *
 * Accessors must fit the regex <code>^([a-zA-Z_$][a-zA-Z0-9_$]*)$</code>.
 */
public class NotationAccessor extends NotationSegment<String> {

    /**
     * Initialize with the provided value.
     *
     * @param value the {@link String} value.
     */
    NotationAccessor(@Nonnull String value) {
        super(value);
    }

    /**
     * Escapes the value for a joiner.
     *
     * @return an escaped value.
     */
    @Nonnull
    @Override
    public String escape() {
        return value();
    }
}