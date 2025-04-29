package pr0gramm3r101.util;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class SimpleTable<T> implements Table<T> {
    public final ArrayList<ArrayList<T>> items;

    public SimpleTable() {
        items = new ArrayList<>();
    }

    public SimpleTable(int rows, int columns) {
        this();
        for (int i = 0; i < rows; i++) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(null);
            }

            items.add(row);
        }
    }

    /**
     * Fills in all the values with <code>null</code>.
     * @deprecated This method is unstable, and it may freeze the code. Don't use it.
     */
    @Deprecated(forRemoval = true)
    private void fillItems() {
        for (int i = 0; i < getMaxRowLength(); i++) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = 0; j < getMaxColumnHeight(); j++) {
                row.add(null);
            }

            items.add(row);
        }
    }

    public int getMaxRowLength() {
        int max = 0;
        for (ArrayList<T> row : items) {
            max = Math.max(max, row.size());
        }
        return max;
    }

    public int getMaxColumnHeight() {
        int max = 0;
        for (Column<T> col: getColumns()) {
            max = Math.max(max, col.size());
        }
        return max;
    }

    @Override
    public @Nullable T get(int row, int column) throws IndexOutOfBoundsException {
        ArrayList<T> items;
        T result;
        items = this.items.get(row);
        try {
            result = items.get(column);
        } catch (IndexOutOfBoundsException _) {
            for (int i = 0; i < items.size() - column; i++) {
                items.add(null);
            }
            return null;
        }

        return result;
    }
    
    @Override
    public void set(int row, int column, @Nullable T value) {
        ArrayList<T> r;
        while (true) {
            try {
                r = items.get(row);
                break;
            } catch (IndexOutOfBoundsException _) {
                items.add(new ArrayList<>());
            }
        }

        while (true) {
            try {
                r.set(column, Objects.requireNonNull(value));
                break;
            } catch (IndexOutOfBoundsException _) {
                r.add(null);
            }
        }
        // fillItems();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Row<T>[] getRows() {
        Row<T>[] result = new Row[items.size()];
        for (int i = 0; i < items.size(); i++) {
            result[i] = new Row<>(items.get(i));

            if (result[i].size() != items.size()) {
                result[i].set(result[i].size() - 1, null);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Column<T>[] getColumns() {
        ArrayList<Column<T>> result = new ArrayList<>();
        int rowLen = getMaxRowLength();
        int size = items.size();

        for (int col = 0; col < rowLen; col++) {
            Column<T> column = new Column<>();
            for (int row = 0; row < size; row++) {
                try {
                    column.set(row, get(row, col));
                } catch (IndexOutOfBoundsException _) {
                    column.set(row, null);
                }
            }

            result.add(column);
        }

        Column<T>[] result2 = new Column[result.size()];
        for (int i = 0; i < result.size(); i++) {
            result2[i] = result.get(i);
        }

        return result2;
    }
}
