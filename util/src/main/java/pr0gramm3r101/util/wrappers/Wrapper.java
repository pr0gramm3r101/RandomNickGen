package pr0gramm3r101.util.wrappers;

import org.jetbrains.annotations.Nullable;

/**
 * Wrapper for any type.
 * It is useful for values that need to be final but they will change.
 * <br/>
 * Example:
 * <pre>
 *     {@code
 *     int test = 1;
 *
 *     Runnable run = () -> Objects.requireNonNull(test); // test needs to be final, but it's not
 *
 *     test = 2;
 *     run.run();
 *     }
 * </pre>
 * Solution:
 * <pre>
 *     {@code
 *     Wrapper<Integer> test = new Wrapper<>(1);
 *
 *     Runnable run = () -> Objects.requireNonNull(test.get()); // this will work, because test won't change
 *
 *     test.set(2);
 *     run.run();
 *     }
 * </pre>
 * @param <T> the type to be wrapped
 */
public class Wrapper<T> {
    /**
     * Stores the value.
     * @see Wrapper#get()
     * @see Wrapper#set(T value)
     */
    private @Nullable T value;

    /**
     * Constructs a {@link Wrapper} with the default value of <code>null</code>.
     */
    public Wrapper() {
        this(null);
    }

    /**
     * Constructs a {@link Wrapper} with the given value.
     * @param value The initial value. Can be <code>null</code>.
     */
    public Wrapper(@Nullable T value) {
        set(value);
    }

    /**
     *
     * @return The value that is stored inside the wrapper. Can be <code>null</code>.
     */
    public @Nullable T get() {
        return value;
    }

    /**
     * Sets the stored value to the given one.
     * @param value The value to replace the old one with. Can be <code>null</code>.
     */
    public void set(@Nullable T value) {
        this.value = value;
    }

    /**
     * Direct link to the stored object's <code>toString()</code> method.
     * @return The string representation of the stored object or <code>null</code>.
     */
    @Override
    public String toString() {
        return value == null ? "null" : value.toString();
    }

    /**
     * Direct link to the stored object's <code>hashCode()</code> method.
     * @return The hash code of the stored object or this {@link Wrapper}.
     */
    @Override
    public int hashCode() {
        return value == null ? super.hashCode() : value.hashCode();
    }
}
