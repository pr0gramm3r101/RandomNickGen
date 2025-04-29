package pr0gramm3r101.util.io;

import java.io.OutputStream;

/**
 * An empty output stream.
 * Package-private because it's only needed for {@link CombinedPrintStreams}.
 */
final class NullOutputStream extends OutputStream {
    /**
     * Does nothing.
     * @param b Ignored.
     */
    @Override public void write(int b) {}
}