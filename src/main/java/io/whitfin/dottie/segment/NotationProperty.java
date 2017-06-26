package io.whitfin.dottie.segment;

import javax.annotation.Nonnull;

/**
 * Segment class to represent a notation property.
 *
 * Properties represent fields in the same way as accessor values,
 * however they accept special characters and can begin with numerics.
 */
public class NotationProperty extends NotationSegment<String> {

    /**
     * Initialize with the provided value.
     *
     * @param value the {@link String} value.
     */
    NotationProperty(@Nonnull String value) {
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
        return "[\"" + value().replace("\"", "\\\"") + "\"]";
    }
}
