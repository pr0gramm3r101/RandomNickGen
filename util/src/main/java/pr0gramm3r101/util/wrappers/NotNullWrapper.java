package pr0gramm3r101.util.wrappers;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Another type of {@link Wrapper} that cannot contain <code>null</code> values.
 * @param <T> the type to be wrapped
 * @see Wrapper
 */
public class NotNullWrapper<T> extends Wrapper<T> {
    /**
     * Constructs a new {@link NotNullWrapper} with the given value.
     * @param value The initial value. Cannot be <code>null</code>.
     */
    public NotNullWrapper(@NotNull T value) {
        super(value);
    }

    /**
     * @return The stored value.
     */
    public @NotNull T get() {
        return Objects.requireNonNull(super.get());
    }

    /**
     * Sets the stored value to the given one.
     * @param value The value to replace the old one with. Cannot be <code>null</code>.
     */
    public void set(T value) {
        super.set(Objects.requireNonNull(value));
    }
}
