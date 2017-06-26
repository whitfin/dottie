package io.whitfin.dottie.splitter;

import io.whitfin.dottie.exception.NotationException;
import io.whitfin.dottie.parser.NotationParser;
import io.whitfin.dottie.segment.NotationSegment;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * A splitting class to allow splitting previously joined notation
 * back into a list of {@link NotationSegment} instances.
 *
 * Only one method is exposed at this point, {@link #split(String)}
 * as there is no need for global state. This could change in the
 * future, but it's unlikely.
 */
public class NotationSplitter {

    /**
     * Splits a notational input back into a {@link List} of
     * {@link NotationSegment} instances.
     *
     * @param input
     *      the input notation {@link String}.
     * @return
     *      a {@link List} of parsed {@link NotationSegment} instances.
     */
    @Nonnull
    public static List<NotationSegment> split(@Nonnull String input) {
        // check null value
        requireNonNull(input);

        // setup start
        int position = 0;
        List<NotationSegment> keys = new ArrayList<>();

        // process all input
        while (!input.isEmpty()) {
            // find the segment representation
            String prop = NotationParser.findSegment(input);

            // exit if no match
            if (prop == null) {
                throw new NotationException(input, input.charAt(0), position);
            }

            // add the key
            keys.add(NotationParser.parseSegment(prop));

            // useful lengths
            int inputLen = input.length();
            int propLen = prop.length();

            // store remaining String
            String remainder;

            // if finished, skip rest
            if (inputLen == propLen) {
                input = "";
                continue;
            }

            // trim remainder using prop len
            remainder = input.substring(propLen);

            // check following char for a dot
            boolean isDot = remainder.charAt(0) == '.';

            // check for trailing special character
            if (remainder.length() < 2) {
                throw new NotationException("Unable to parse key with trailing " +
                                            (isDot ? "dot" : "bracket") + "!");
            }

            // check following char for safe prop
            char nextChar = remainder.charAt(1);
            String nextCharStr = String.valueOf(nextChar);

            // dot requires accessor, brace requires prop opener
            boolean hasSafeProps = isDot
                ? NotationParser.isAccessor(nextCharStr)
                : NotationParser.isPropertyOpener(nextChar);

            // error on bad prop
            if (!hasSafeProps) {
                throw new NotationException(input, nextChar, position + propLen + 1);
            }

            // trim trailing dots
            if (isDot) {
                remainder = remainder.substring(1);
            }

            // shift the cursor
            position += (inputLen - remainder.length());
            // swap the input
            input = remainder;
        }

        // return the keys list
        return keys;
    }
}
