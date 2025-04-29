package pr0gramm3r101.util.program.version;

import java.io.Serial;
import java.io.Serializable;

/**
 * An exception that is thrown when attempting to parse an invalid version.
 */
public class VersionFormatException extends NumberFormatException implements Serializable {
    @Serial
    private static final long serialVersionUID = 8575592825389102214L;

    /**
     * Constructs a VersionFormatException with no message.
     */
    public VersionFormatException() {
        super();
    }

    /**
     * Constructs the exception with the given invalid version.
     * @param ver The invalid version
     */
    public VersionFormatException(String ver) {
        super("[%s] is not a valid version!".formatted(ver));
    }
}
