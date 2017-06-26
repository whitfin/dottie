package io.whitfin.dottie.segment;

import io.whitfin.dottie.parser.NotationParser;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

/**
 * Parent segment to be used to represent notation values of
 * various types.
 *
 * @param <T> the type of the notation.
 */
public abstract class NotationSegment<T> {

    /**
     * The internal value instance.
     */
    private final T value;

    /**
     * Initialize with the provided value.
     *
     * @param value the {@link String} value.
     */
    NotationSegment(@Nonnull T value) {
        this.value = requireNonNull(value);
    }

    /**
     * Escapes the value for a joiner.
     *
     * @return an escaped value.
     */
    @Nonnull
    public abstract String escape();

    /**
     * Returns this instance cast as an index segment.
     *
     * @return this instance cast to an index segment.
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public NotationSegment<Integer> asIndex() {
        return (NotationSegment<Integer>) this;
    }

    /**
     * Returns this instance cast as a property segment.
     *
     * @return this instance cast to a property segment.
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public NotationSegment<String> asProperty() {
        return (NotationSegment<String>) this;
    }

    /**
     * Returns whether this instance is an index segment.
     *
     * @return true if an index segment.
     */
    public boolean isIndex() {
        return type() == Integer.class;
    }

    /**
     * Returns whether this instance is a property segment.
     *
     * @return true if a property segment.
     */
    public boolean isProperty() {
        return type() == String.class;
    }

    /**
     * Returns the type of this instance.
     *
     * This can be used to determine whether this is an index
     * or property segment instance.
     *
     * @return the class type of this segment.
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public Class<T> type() {
        return (Class<T>) this.value.getClass();
    }

    /**
     * Returns the generic value of this segment.
     *
     * @return the internal value.
     */
    @Nonnull
    public T value() {
        return value;
    }

    /**
     * Parses an integer value into an index segment.
     *
     * @param index
     *      the index to parse into a segment.
     * @return
     *      a new {@link NotationSegment} instance.
     */
    @Nonnull
    public static NotationSegment parse(@Nonnull Integer index) {
        return new NotationIndex(requireNonNull(index));
    }

    /**
     * Parses a textual value into an property segment.
     *
     * @param property
     *      the property to parse into a segment.
     * @return
     *      a new {@link NotationSegment} instance.
     */
    @Nonnull
    public static NotationSegment parse(@Nonnull String property) {
        return NotationParser.isAccessor(requireNonNull(property))
            ? new NotationAccessor(property)
            : new NotationProperty(property);
    }
}
