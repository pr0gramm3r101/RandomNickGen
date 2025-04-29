package pr0gramm3r101.util.io;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class that has useful byte-related methods.
 */
public final class ByteUtils {
    /**
     * Gets a byte from an {@link Integer}.
     * @param b The byte in {@code int} form.
     * @return The {@link Byte}.
     */
    public static byte getByteFromInt(int b) {
        return Integer.valueOf(b).byteValue();
    }

    /**
     * Converts an array of {@link Byte} objects into their
     * primitive type ({@code byte}).
     * @param bytes The {@link Byte} array to convert
     * @return The {@code byte[]} array.
     */
    @Contract(pure = true)
    public static byte[] convertBytesToPrimitiveType(Byte[] bytes) {
        byte[] result = new byte[bytes.length];

        int i = 0;

        for (Byte b: bytes) {
            result[i] = b;
            i++;
        }
        return result;
    }

    /**
     * Converts a byte to an {@link Integer}.
     * @param b The byte to convert.
     * @return The byte in integer form.
     */
    public static int byteToInt(byte b) {
        return Byte.valueOf(b).intValue();
    }

    /**
     * Converts a {@code byte[]} array to its object representation ({@link Byte}{@code []}).
     * @param bytes The byte array to convert.
     * @return a {@link Byte} array.
     */
    public static Byte[] convertPrimitiveTypeToBytes(byte[] bytes) {
        Byte[] result = new Byte[bytes.length];

        int i = 0;

        for (byte b: bytes) {
            result[i] = b;
            i++;
        }
        return result;
    }

    /**
     * Converts a byte[] array to a string
     * @param bytes the byte[] array to be converted
     * @return the string representation of the byte[] array
     */
    public static @NotNull String bytesToStr(byte[] bytes) {
        return new String(bytes);
    }
}