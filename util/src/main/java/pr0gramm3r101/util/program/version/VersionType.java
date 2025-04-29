package pr0gramm3r101.util.program.version;

/**
 * Contains common version types.
 * @see Version
 */
public final class VersionType {
    /**
     * No instances
     */
    private VersionType() { throw new UnsupportedOperationException(); }

    /**
     * No version.
     */
    public static final String NONE = "";
    /**
     * Pre-alpha
     */
    public static final String PRE_ALPHA = "pre-alpha";
    /**
     * Alpha
     */
    public static final String ALPHA = "alpha";
    /**
     * Beta
     */
    public static final String BETA = "beta";
    /**
     * Release
     */
    public static final String RELEASE = "release";
    /**
     * Snapshot
     */
    public static final String SNAPSHOT = "snapshot";
}
