package pr0gramm3r101.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Contains utilities for strings.
 */
public final class StringUtils {
    /**
     * Utility class
     */
    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Joins all the specified values together.
     * <br/>
     * Example:
     * <pre>
     *     {@code
     *     String[] values = new String[] {"a", "b", "c", "d"}
     *     
     *     String result = StringUtils.join("and", values);
     *     // result: "a and b and c and d"
     *     }
     * </pre>
     * @param delimiter The delimiter
     * @param values The values to join
     * @return The values separated by the delimiter
     */
    public static @NotNull String join(@NotNull CharSequence delimiter, CharSequence... values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Joins all the specified values together.
     * @param delimiter The delimiter
     * @param values The values to join
     * @return The values separated by the delimiter
     * @see #join(CharSequence, CharSequence...)
     */
    public static @NotNull String join(char delimiter, CharSequence... values) {
        return join(String.valueOf(delimiter), values);
    }

    /**
     * Joins all the specified values together.
     * @param delimiter The delimiter
     * @param values The values to join
     * @return The values separated by the delimiter
     * @see #join(char, CharSequence...) 
     */
    public static String join(char delimiter, char... values) {
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = String.valueOf(values[i]);
        }
        return join(delimiter, result);
    }

    /**
     * Joins all the specified values together.
     * @param delimiter The delimiter
     * @param values The values to join
     * @return The values separated by the delimiter
     * @see #join(CharSequence, CharSequence...) 
     */
    public static String join(CharSequence delimiter, ArrayList<?> values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            sb.append(values.get(i));
            if (i < values.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Joins all the specified values together.
     * @param delimiter The delimiter
     * @param values The values to join
     * @return The values separated by the delimiter
     * @see #join(char, CharSequence...) 
     */
    public static String join(char delimiter, ArrayList<?> values) {
        return join(String.valueOf(delimiter), values);
    }
}
