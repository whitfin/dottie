package io.whitfin.dottie.parser;

import io.whitfin.dottie.segment.NotationSegment;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsing class to deal with all regex required to read back properties.
 *
 * This class will carry out all parsing of segments in order to keep
 * implementation classes smaller and not expose pattern knowledge externally.
 */
public class NotationParser {

    /**
     * A set to store all valid property openers (0-9, ' and ").
     */
    private static final Set<Character> OPENERS = new HashSet<>();
    static {
        for (int i = 48; i < 58; i++) {
            OPENERS.add((char) i);
        }
        OPENERS.add('"');
        OPENERS.add('\'');
    }

    /**
     * A pattern to use when matching accessor values.
     */
    private static final Pattern ACCESSOR = Pattern.compile("^([a-zA-Z_$][a-zA-Z0-9_$]*)$");

    /**
     * A pattern to use when matching against index values.
     */
    private static final Pattern INDEX = Pattern.compile("^\\[([0-9]+)]$");

    /**
     * A pattern to use when matching against property values.
     */
    private static final Pattern PROPERTY = Pattern.compile("^\\[(?:'|\")(.*)(?:'|\")]$");

    /**
     * A pattern to use when matching a segment, which is any of the above.
     */
    private static final Pattern SEGMENT = Pattern
        .compile("^((?:[a-zA-Z_$][a-zA-Z0-9_$]*)|(?:\\[(?:'.*?(?='])'|\".*?(?=\"])\")])|(?:\\[\\d+]))");

    /**
     * A closed version of the segment pattern, to ensure a full match.
     */
    private static final Pattern CLOSED_SEGMENT = Pattern.compile(SEGMENT.toString() + "$");

    /**
     * Finds a segment at the start of the input string, if possible.
     *
     * @param input
     *      the input string to read back from.
     * @return
     *      a String segment match, if found.
     */
    @Nullable
    public static String findSegment(@Nonnull String input) {
        return findFirstMatch(input, SEGMENT);
    }

    /**
     * Returns whether the input is an accessor.
     *
     * @param input
     *      the input value to verify against.
     * @return
     *      true if the input is an accessor.
     */
    public static boolean isAccessor(@Nonnull String input) {
        return isPattern(input, ACCESSOR);
    }

    /**
     * Returns whether the input is an index.
     *
     * @param input
     *      the input value to verify against.
     * @return
     *      true if the input is an index.
     */
    public static boolean isIndex(@Nonnull String input) {
        return isPattern(input, INDEX);
    }

    /**
     * Returns whether the input is a property.
     *
     * @param input
     *      the input value to verify against.
     * @return
     *      true if the input is a property.
     */
    public static boolean isProperty(@Nonnull String input) {
        return isPattern(input, PROPERTY);
    }

    /**
     * Returns whether the input character is a property opener.
     *
     * @param input
     *      the input character to verify against.
     * @return
     *      true if the input is an opener.
     */
    public static boolean isPropertyOpener(char input) {
        return OPENERS.contains(input);
    }

    /**
     * Returns whether the input is a valid segment.
     *
     * @param input
     *      the input value to verify against.
     * @return
     *      true if the input is a valid segment.
     */
    public static boolean isSegment(@Nonnull String input) {
        return isPattern(input, CLOSED_SEGMENT);
    }

    /**
     * Parses a previously found segment into a {@link NotationSegment}
     * instance.
     *
     * This method will throw an exception if the provided segment is
     * invalid or null.
     *
     * @param segment
     *      the input segment to parse out.
     * @return
     *      an instance of a {@link NotationSegment}.
     */
    public static NotationSegment parseSegment(@Nonnull String segment) {
        Objects.requireNonNull(segment);

        String tmp;

        if ((tmp = findFirstMatch(segment, ACCESSOR)) != null) {
            return NotationSegment.parse(tmp);
        }

        if ((tmp = findFirstMatch(segment, INDEX)) != null) {
            return NotationSegment.parse(Integer.parseInt(tmp));
        }

        if ((tmp = findFirstMatch(segment, PROPERTY)) != null) {
            return NotationSegment.parse(tmp);
        }

        throw new IllegalStateException();
    }

    /**
     * Finds the first match of a pattern against an input.
     *
     * @param input
     *      the input to match against.
     * @param pattern
     *      the pattern to use when matching.
     * @return
     *      the first group if possible, or null.
     */
    @Nullable
    private static String findFirstMatch(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Checks whether an input matches a pattern.
     *
     * @param input
     *      the input to match against.
     * @param pattern
     *      the pattern to use when matching.
     * @return
     *      true if the input matches the pattern.
     */
    private static boolean isPattern(String input, Pattern pattern) {
        return pattern.matcher(input).find();
    }
}
