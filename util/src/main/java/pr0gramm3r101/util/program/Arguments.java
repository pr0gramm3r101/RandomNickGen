package pr0gramm3r101.util.program;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class helps to parse console arguments easily.
 */
public class Arguments implements Serializable {
    /**
     * The arguments.
     */
    private final String[] arguments;

    /**
     * Constructs the parser with an empty arguments list.
     */
    public Arguments() {
        this(new String[0]);
    }

    /**
     * Constructs the parser with the given arguments.
     * @param arguments The arguments
     */
    public Arguments(String... arguments) {
        this.arguments = arguments;
    }

    /**
     * @return {@link #arguments}.
     */
    public String[] getArguments() {
        return arguments;
    }

    /**
     * Returns an argument at the specified index.
     * @param index The index.
     * @return The argument.
     */
    public String getArgument(int index) {
        return arguments[index];
    }

    /**
     * @return The length of {@link #arguments}.
     */
    public int getArgumentCount() {
        return arguments.length;
    }

    /**
     * @return A string representation of {@link #arguments}.
     */
    @Override
    public String toString() {
        return Arrays.toString(arguments);
    }

    /**
     * @return The arguments map with prefix {@code --}.
     */
    public Map<String, String> getArgumentsMap() {
        return getArgumentsMap("--");
    }

    /**
     * Returns a map of keys and values of arguments
     * with the specified prefix.
     * @param prefix The prefix.
     * @return The arguments map.
     */
    public Map<String, String> getArgumentsMap(String prefix) {
        String prevArg = "";

        Map<String, String> map = new HashMap<>();

        for (String argument : arguments) {
            if (prevArg.startsWith(prefix)) {
                map.put(prevArg.substring(prefix.length()), argument);
            }

            prevArg = argument;

        }
        return map;
    }

    /**
     * @param argument The argument.
     * @return <code>true</code> if the argument exists, and <code>false</code> otherwise
     */
    public boolean hasArgument(String argument) {
        boolean hasArgument;
        hasArgument = getArgumentsMap("--").containsKey(argument);
        if (!hasArgument) {
            hasArgument = Arrays.asList(arguments).contains(argument);
        }

        return hasArgument;
    }

    /**
     * @param prefix The prefix.
     * @param argument The argument.
     * @return <code>true</code> if the argument exists, <code>false</code> otherwise.
     */
    public boolean hasArgument(String prefix, String argument) {
        return getArgumentsMap(prefix).containsKey(argument);
    }

    /**
     * @param argument The argument.
     * @return The argument value.
     */
    public String getArgument(String argument) {
        return getArgumentsMap("--").get(argument);
    }

    /**
     * @param prefix The prefix.
     * @param argument The argument.
     * @return The argument value.
     */
    public String getArgument(String prefix, String argument) {
        return getArgumentsMap(prefix).get(argument);
    }

    /**
     * Checks if the {@link #arguments} arrays of both objects match.
     * @param o The object to compare against.
     * @return <code>true</code> if both objects match, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Arguments) return Arrays.equals(this.arguments, ((Arguments) o).arguments);
        return false;
    }
}
