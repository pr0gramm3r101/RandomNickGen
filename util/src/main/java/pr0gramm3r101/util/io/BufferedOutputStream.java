package pr0gramm3r101.util.io;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.function.Consumer;

import static pr0gramm3r101.util.io.ByteUtils.bytesToStr;
import static pr0gramm3r101.util.io.ByteUtils.convertBytesToPrimitiveType;

/**
 * A {@link OutputStream} implementation with a buffer
 * that stores all bytes that were written to this stream.
 * This buffer will clear when a byte representing a line break ({@code \n})
 * was written to this stream. Also it will call the {@link #onFlush} consumer with
 * the data stored in the buffer before clearing.
 */
public class BufferedOutputStream extends OutputStream {
    /**
     * The buffer of this stream.
     */
    private final ArrayList<Byte> buffer = new ArrayList<>();
    /**
     * The linebreak character. Default is {@code \n}.
     */
    private final char linebreak;
    /**
     * The function that will be called when a {@link #linebreak}
     * was written to this stream with all the characters that were written.
     */
    private final Consumer<String> onFlush;

    /**
     * Constructs a new {@link BufferedOutputStream}.
     * @param linebreak The line break character.
     * @param onFlush See {@link #onFlush}.
     * @see #onFlush
     * @see #linebreak
     */
    public BufferedOutputStream(char linebreak, Consumer<String> onFlush) {
        this.linebreak = linebreak;
        this.onFlush = onFlush;
    }

    /**
     * Constructs a buffered output stream with the default {@link #linebreak}.
     * @param onFlush See {@link #onFlush}.
     */
    public BufferedOutputStream(Consumer<String> onFlush) {
        this('\n', onFlush);
    }

    /**
     * Constructs a buffered output stream with the default {@link #linebreak}
     * and an empty {@link #onFlush} function.
     */
    public BufferedOutputStream() {
        this((_) -> {});
    }

    /**
     * {@inheritDoc}
     *
     * @param b {@inheritDoc}
     */
    @Override
    public void write(int b) {
        buffer.add((byte) b);

        String byteString = bytesToStr(convertBytesToPrimitiveType(buffer.toArray(new Byte[0])));

        if (byteString.contains(String.valueOf(linebreak))) {
            onFlush.accept(byteString.replaceAll(String.valueOf(linebreak), ""));
            buffer.clear();
        }
    }
}