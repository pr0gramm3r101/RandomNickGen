package pr0gramm3r101.util.io;

import java.io.PrintStream;

/**
 * A {@link PrintStream} that directs input to multiple streams.
 */
public class CombinedPrintStreams extends PrintStream {
    /**
     * An array of all {@link PrintStream}s that the input will be directed to.
     */
    private final PrintStream[] outputStreams;

    /**
     * Constructs a new bundle of {@link PrintStream}s.
     * @param streams The output streams.
     * @see #outputStreams
     */
    public CombinedPrintStreams(PrintStream... streams) {
        super(new NullOutputStream());

        this.outputStreams = streams;
    }

    /**
     * Writes the specified byte to all {@link PrintStream}s that this object contains.
     *
     * @param b {@inheritDoc}
     */
    @Override
    public void write(int b) {
        for (PrintStream stream : outputStreams) {
            stream.write(b);
        }
    }
}

