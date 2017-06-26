package io.whitfin.dottie.joiner;

import io.whitfin.dottie.segment.NotationSegment;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

/**
 * Joining class for notation, to allow joining many property keys
 * efficiently and fluently.
 *
 * Uses a {@link StringBuilder} under the hood for performance reasons,
 * and due to the inability to extend. A joiner instance can only append,
 * due to the nature of how notation works it would be unnecessary and
 * complex to implement removal.
 */
public class NotationJoiner {

    /**
     * The internal builder instance for joining.
     */
    private final StringBuilder internalBuilder;

    /**
     * Flag to store started state or not.
     */
    private boolean started;

    /**
     * Initializes the internal builder.
     */
    public NotationJoiner() {
        this.internalBuilder = new StringBuilder();
        this.reset();
    }

    /**
     * Appends some amount of index values to the builder.
     *
     * @param indices
     *      the index values to add to the joiner.
     * @return
     *      this {@link NotationJoiner} for chaining.
     */
    @Nonnull
    public NotationJoiner append(@Nonnull Integer... indices) {
        for (Integer index : requireNonNull(indices)) {
            this.append(NotationSegment.parse(index));
        }
        return this;
    }

    /**
     * Appends some existing segments to the builder.
     *
     * @param segments
     *      the segment values to add to the joiner.
     * @return
     *      this {@link NotationJoiner} for chaining.
     */
    @Nonnull
    public NotationJoiner append(@Nonnull NotationSegment... segments) {
        for (NotationSegment segment : requireNonNull(segments)) {
            String escapedKey = requireNonNull(segment).escape();

            if (this.started) {
                if (escapedKey.charAt(0) != '[') {
                    this.internalBuilder.append(".");
                }
            } else {
                this.started = true;
            }

            this.internalBuilder.append(escapedKey);
        }
        return this;
    }

    /**
     * Appends some amount of property values to the builder.
     *
     * @param properties
     *      the property values to add to the joiner.
     * @return
     *      this {@link NotationJoiner} for chaining.
     */
    @Nonnull
    public NotationJoiner append(@Nonnull String... properties) {
        for (String property : requireNonNull(properties)) {
            this.append(NotationSegment.parse(property));
        }
        return this;
    }

    /**
     * Converts the current state of the builder to a {@link String}.
     *
     * @return a new {@link String} instance.
     */
    @Nonnull
    public String print() {
        return this.internalBuilder.toString();
    }

    /**
     * Resets the joiner to an initial state.
     *
     * @return this {@link NotationJoiner} for chaining.
     */
    @Nonnull
    public NotationJoiner reset() {
        this.internalBuilder.setLength(0);
        this.started = false;
        return this;
    }

    /**
     * Converts this instance to a {@link String}.
     *
     * @return a {@link String} representation.
     */
    @Nonnull
    @Override
    public String toString() {
        return print();
    }
}
