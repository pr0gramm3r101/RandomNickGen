package pr0gramm3r101.util.console;

/**
 * Contains console box drawing characters.
 */
public final class BoxDraw {
    private BoxDraw() {
        throw new UnsupportedOperationException();
    }

    public static final char TOP_LEFT = '┌';
    public static final char TOP_RIGHT = '┐';
    public static final char BOTTOM_LEFT = '└';
    public static final char BOTTOM_RIGHT = '┘';

    public static final char VERTICAL = '│';
    public static final char HORIZONTAL = '─';

    public static final char TOP_LEFT_RIGHT = '┬';
    public static final char BOTTOM_LEFT_RIGHT = '┴';
    public static final char TOP_BOTTOM_RIGHT = '├';
    public static final char TOP_BOTTOM_LEFT = '┤';

    public static final char ALL = '┼';
}
