package io.whitfin.dottie.segment;

import javax.annotation.Nonnull;

/**
 * Segment class to represent a notation index.
 *
 * An index is a numeric value representing an Array location.
 */
public class NotationIndex extends NotationSegment<Integer> {

    /**
     * Initialize with the provided value.
     *
     * @param value the {@link String} value.
     */
    NotationIndex(@Nonnull Integer value) {
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
        return "[" + value() + "]";
    }
}
