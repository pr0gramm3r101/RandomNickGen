package pr0gramm3r101.util.program.version;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * A class representing a version with a type.
 * The default type is {@link VersionType#RELEASE}.
 */
public class Version implements Comparable<Version>, Serializable {
    /**
     * Version UID for the serialization mechanism.
     * @see Serializable
     * @see java.io.Externalizable
     * @see Serial
     */
    @Serial
    private static final long serialVersionUID = -7553020996846490992L;

    /**
     * The version points.
     * <br/>
     * Example:
     * <pre>
     *     {@code
     *     protected final int[] versionPoints = new int[]{1,0} // version 1.0
     *     }
     * </pre>
     */
    protected final int[] versionPoints;
    /**
     * The version type.
     * @see VersionType
     */
    protected final String type;

    /**
     * Constructs a Version of type {@linkplain VersionType#RELEASE Release} and with one point that is {@code 0}.
     */
    public Version() {
        this(VersionType.RELEASE,0);
    }

    /**
     * Constructs a new Version.
     * @param type The type.
     * @param versionPoints The version points.
     * @see #type
     * @see #versionPoints
     */
    public Version(String type, int... versionPoints) {
        this.type = type;
        this.versionPoints = versionPoints;
    }

    /**
     * Constructs a new Version out of a version string.
     * <br/>
     * Example:
     * <pre>
     *     {@code
     *     1.0 ALPHA
     *     }
     * </pre>
     * will result in:
     * <pre>
     *     {@code
     *     protected final int[] versionPoints = new int[] {1,0};
     *     protected final String type = "ALPHA";
     *     }
     * </pre>
     * @param version The version string in format {@code <version> <type>}
     */
    public Version(@NotNull String version) {
        this(getVersionType(version),getVersion(version));
    }

    /**
     * Gets the version type out of a version string.
     * @param version The version string in format {@code <version> <type>}.
     * @return The 2nd word of the version string.
     */
    @Contract(pure = true)
    private static @NotNull String getVersionType(@NotNull String version) {
        String[] parts = version.split("\\s");

        String type = VersionType.RELEASE;
        try {
            type = parts[1];
        } catch (ArrayIndexOutOfBoundsException _) {
        }

        return type;
    }

    /**
     * Parses the version points from a version string.
     * @param version The version string in format {@code <version> <type>}.
     * @return The parsed version points as {@code int[]}.
     */
    private static int[] getVersion(@NotNull String version) {
        try {
            String[] parsedVersion = version.split("\\s");

            return Arrays.stream(parsedVersion[0].split("\\.")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new VersionFormatException(version);
        }

    }


    /**
     * Converts this Version into a version string.
     * <br/>
     * Example:
     * <pre>
     *     {@code
     *     protected final int[] versionPoints = new int[] {1, 1, 3};
     *     protected final String type = "BETA";
     *     }
     * </pre>
     * will be:
     * <pre>
     *     {@code
     *     1.1.3 BETA
     *     }
     * </pre>
     * @return A version string in format {@code <version> <type>}.
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(".");
        for (int versionPoint : versionPoints) {
            sj.add(String.valueOf(versionPoint));
        }


        return sj + " " + type;
    }

    /**
     * Compares two types for equality.
     * <ul>
     *     <li>
     *         {@link Version} -> checks if {@link #versionPoints} in this object
     *          is equal to the {@linkplain #versionPoints value} inside the provided object
     *          and the {@linkplain  #type version type} in this object
     *          is equal to the {@linkplain #type value} inside the provided object.
     *     </li>
     *     <li>
     *         {@link String} -> checks if the {@link #toString()} method result is equal
     *         to the provided version string.
     *     </li>
     *     <li>
     *         {@link Integer} -> checks if {@link #versionPoints} length is {@code 1}, and
     *         the first element matches the provided integer.
     *     </li>
     *     <li>
     *         {@link Float} or {@link Double} -> checks if their
     *         {@linkplain Float#toString() string representation} + {@linkplain #type version type}
     *         matches this object's {@link #toString()} method result.
     *     </li>
     * </ul>
     * If any {@linkplain Throwable error} occurs, returns {@code false}.
     * <br/>
     * If the object is {@code null}, returns {@code false}.
     * @param o The object to compare with.
     * @return <code>true</code> if the objects match, <code>false</code> otherwise
     */
    @Override
    public boolean equals(@Nullable Object o) {
        if (o == null) return false;
        try {
            if (o instanceof Version version) {
                return Arrays.equals(this.versionPoints, version.versionPoints) && this.type.equals(version.type);
            } else if (o instanceof String s) {
                return this.toString().equals(s);
            } else if (o instanceof Integer i) {
                return this.versionPoints.length == 1 && this.versionPoints[0] == i;
            } else if (o instanceof Float || o instanceof Double) {
                double version = (float) o;

                return (version + " " + type).equals(this.toString());
            }
        } catch (Exception _) {}
        return false;
    }

    /**
     * @return The {@link #toString()} method result's {@linkplain String#hashCode() hash code}.
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * @param index The index of the version point.
     * @return {@link #versionPoints}{@code [index]}.
     */
    public int getPoint(int index) {
        return this.versionPoints[index];
    }


    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int compareTo(@NotNull Version o) {
        for (int i = 0; i < this.versionPoints.length; i++) {
            if (this.versionPoints[i] > o.versionPoints[i]) {
                return 1;
            } else if (this.versionPoints[i] < o.versionPoints[i]) {
                return -1;
            }
            if (i == this.versionPoints.length - 1) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * @return {@link #versionPoints}.
     */
    public int[] getPoints() {
        return versionPoints;
    }

    /**
     * @return {@link #type}.
     */
    public String getType() {
        return type;
    }

    /**
     * @return If {@link #type} equals to {@link VersionType#SNAPSHOT} {@code true}, {@code false} otherwise.
     */
    public boolean isSnapshot() {
        return type.equals(VersionType.SNAPSHOT);
    }
}
