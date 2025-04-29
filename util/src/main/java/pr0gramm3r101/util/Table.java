package pr0gramm3r101.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pr0gramm3r101.util.console.BoxDraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static pr0gramm3r101.util.console.BoxDraw.*;

/**
 * A table with rows and columns.
 * @param <T> the type of the values inside it
 */
@SuppressWarnings("unused")
public interface Table<T> {
    /**
     * @param row The row in which the item is located.
     * @param column The column in which the item is located.
     * @return The item at the specified coordinates.
     * @throws IndexOutOfBoundsException if the item doesn't exist
     */
    T get(int row, int column) throws IndexOutOfBoundsException;
    /**
     * Sets the item at the specified coordinates.
     * @param row The row in which the item is located.
     * @param column The column in which the item is located.
     * @param value The value to replace the old one with.
     * @throws IndexOutOfBoundsException if the coordinates are out of bounds
     */
    void set(int row, int column, T value) throws IndexOutOfBoundsException;

    /**
     * @return The number of rows in this table.
     */
    default int getRowsCount() {
        return getRows().length;
    }
    /**
     * @return The number of columns in this table.
     */
    default int getColumnsCount() {
        return getColumns().length;
    }

    /**
     * Gets the row of the given number.
     * @param row The row number.
     * @return A {@link Row} that represents the row in this table.
     * @throws IndexOutOfBoundsException if the row doesn't exist
     */
    default Row<T> getRow(int row) throws IndexOutOfBoundsException {
        return getRows()[row];
    }

    /**
     * Gets the column of the given number.
     * @param column The column number.
     * @return A {@link Column} that represents the column in this table.
     * @throws IndexOutOfBoundsException if the column doesn't exist
     */
    default Column<T> getColumn(int column) throws IndexOutOfBoundsException {
        return getColumns()[column];
    }

    /**
     * An array of all rows present in this table.
     * @return Array of {@link Row}s.
     */
    Row<T>[] getRows();
    /**
     * An array of all columns present in this table.
     * @return Array of {@link Column}s.
     */
    Column<T>[]  getColumns();

    /**
     * A single row from a table.
     * It implements the {@link Table} interface for easier management.
     * @param <T> The type of the items in this row.
     */
    class Row<T> implements Table<T> {
        /**
         * The items in this row.
         */
        private final ArrayList<T> items;

        /**
         * Creates a new empty row.
         */
        public Row() {
            items = new ArrayList<>();
        }

        /**
         * Creates a new row with the given items from an {@link ArrayList}.
         * @param items The initial items that this row will have.
         */
        public Row(ArrayList<T> items) {
            this.items = items;
        }

        /**
         * Creates a new row with the given items, in varargs/array form.
         * @param items The initial items that this row will have.
         */
        @SafeVarargs
        public Row(T... items) {
            this.items = new ArrayList<>();

            Collections.addAll(this.items, items);
        }

        /**
         * Gets an item from this column.
         * @param row Ignored. This parameter is needed only to properly implement the {@link Table} interface.
         * @param column The column in which the item is located.
         * @return The item.
         * @see Row#get(int)
         */
        public T get(int row, int column) {
            return get(column);
        }

        /**
         * Returns the item at the specified index.
         * @param index The index. Can be out of bounds.
         * @return The item, or <code>null</code> if it doesn't exist.
         */
        public @Nullable T get(int index) {
            try {
                return items.get(index);
            } catch (IndexOutOfBoundsException _) {
                return null;
            }
        }

        /**
         * Sets the value at the specified index.
         * Automatically creates new slots if the index is out of bounds.
         * @param index The index. Can be out of bounds, but not negative.
         * @param value The new value. Can be <code>null</code>.
         * @throws IndexOutOfBoundsException if the index is negative
         */
        public void set(int index, T value) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " is less than 0");
            }

            while (true) {
                try {
                    items.set(index, value);
                    return;
                } catch (IndexOutOfBoundsException _) {
                    items.add(null);
                }
            }
        }

        /**
         * Sets the value at the specified index to a new specified value.
         * @param row Ignored. This parameter is needed only to properly implement the {@link Table} interface.
         * @param column The column in which the item is located.
         * @param value The value to replace the old one with. Can be <code>null</code>.
         * @see Row#set(int, T)
         */
        public void set(int row, int column, T value) {
            set(column, value);
        }

        /**
         * The count of elements in this row.
         * @return The size of {@link Row#items}.
         */
        public int size() {
            return items.size();
        }

        /**
         * @return An array with this object.
         */
        @SuppressWarnings("unchecked")
        public Row<T>[] getRows() {
            return new Row[]{this};
        }

        /**
         * @return An array with {@link Column} objects representing 1 item from this row.
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        public Column<T>[] getColumns() {
            Column[] columns = new Column[items.size()];
            for (int i = 0; i < items.size(); i++) {
                columns[i] = new Column<>(items.get(i));
            }
            return columns;
        }
    }

    /**
     * A single column from a table.
     * It implements the {@link Table} interface for easier management.
     * @param <T> The type of the items in this column.
     */
    class Column<T> implements Table<T> {
        /**
         * The items in this column.
         */
        private final ArrayList<T> items;

        /**
         * Creates a new empty column.
         */
        public Column() {
            items = new ArrayList<>();
        }

        /**
         * Creates a new column with the given items from an {@link ArrayList}.
         * @param items The initial items that this column will have.
         */
        public Column(ArrayList<T> items) {
            this.items = items;
        }

        /**
         * Creates a new column with the given items, in varargs/array form.
         * @param items The initial items that this column will have.
         */
        @SafeVarargs
        public Column(T... items) {
            this.items = new ArrayList<>();

            Collections.addAll(this.items, items);
        }

        /**
         * Gets an item from this column.
         * @param row The row in which the item is located.
         * @param column Ignored. This parameter is only needed to properly implement the {@link Table} interface.
         * @return The item.
         * @see #get(int)
         */
        public T get(int row, int column) {
            return get(row);
        }

        /**
         * Returns the item at the specified index.
         * @param index The index. Can be out of bounds.
         * @return The item, or <code>null</code> if it doesn't exist.
         */
        public @Nullable T get(int index) {
            try {
                return items.get(index);
            } catch (IndexOutOfBoundsException _) {
                return null;
            }
        }

        /**
         * Sets the value at the specified index.
         * Automatically creates new slots if the index is out of bounds.
         * @param index The index. Can be out of bounds, but not negative.
         * @param value The new value. Can be <code>null</code>.
         * @throws IndexOutOfBoundsException if the index is negative
         */
        public void set(int index, T value) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " is less than 0");
            }

            while (true) {
                try {
                    items.set(index, value);
                    return;
                } catch (IndexOutOfBoundsException _) {
                    items.add(null);
                }
            }
        }

        /**
         * Sets the value at the specified index to a new specified value.
         * @param row The row in which the item is located.
         * @param column Ignored. This parameter is needed only to properly implement the {@link Table} interface.
         * @param value The value to replace the old one with.
         * @see #set(int, T)
         */
        public void set(int row, int column, T value) {
            set(row, value);
        }

        /**
         * The count of element in this row.
         * @return The size of {@link #items}.
         */
        public int size() {
            return items.size();
        }

        /**
         * @return An array with this object.
         */
        @SuppressWarnings("unchecked")
        @Override
        public Column<T>[] getColumns() {
            return new Column[]{this};
        }

        /**
         * @return An array with {@link Row} objects representing 1 item from this column.
         */
        @SuppressWarnings({"rawtypes", "unchecked"})
        public Row<T>[] getRows() {
            Row[] rows = new Row[items.size()];
            for (int i = 0; i < items.size(); i++) {
                rows[i] = new Row<>(items.get(i));
            }
            return rows;
        }
    }

    /**
     * Converts a table to its {@link String} representation.
     * The table will be formatted with the ASCII box drawing characters.
     * Example:
     * <pre>
     *     {@code
     * ┌────┬────┬──┐
     * │test│item│#1│
     * ├────┼────┼──┤
     * │test│item│#2│
     * └────┴────┴──┘
     *     }
     * </pre>
     *
     * @param table The table.
     * @return A formatted table with borders.
     * @see BoxDraw
     */
    static @NotNull String asString(@NotNull Table<?> table) {
        StringBuilder sb = new StringBuilder();
        sb.append(TOP_LEFT);

        // Initial column size
        Column<?>[] columns = table.getColumns();
        Map<Column<?>, Integer> sizes = new HashMap<>();
        for (int i = 0; i < columns.length; i++) {
            Column<?> column = columns[i];
            int maxLength = 0;
            for (Row<?> row : column.getRows()) {
                int val = String.valueOf(row.get(0)).length();

                if (val > maxLength) {
                    maxLength = val;
                }
            }

            sb.append(String.valueOf(HORIZONTAL).repeat(maxLength));
            sizes.put(column, maxLength);

            if (i == columns.length - 1) {
                sb.append(TOP_RIGHT);
            } else {
                sb.append(TOP_LEFT_RIGHT);
            }
        }
        Row<?>[] rows = table.getRows();
        sb.append("\n");

        for (int i = 0; i < rows.length; i++) {
            sb.append(VERTICAL);
            Row<?> row = rows[i];
            for (int j = 0; j < columns.length; j++) {
                Column<?> column = columns[j];

                try {
                    sb.append(row.items.get(j)).append(VERTICAL);
                } catch (IndexOutOfBoundsException _) {
                    sb.repeat(" ", sizes.get(column)).append(VERTICAL);
                }

            }
            sb.append("\n");

            if (i == rows.length - 1) {
                sb.append(BOTTOM_LEFT);
            } else {
                sb.append(TOP_BOTTOM_RIGHT);
            }

            for (int j = 0; j < columns.length; j++) {
                Column<?> column = columns[j];
                int maxLength = sizes.get(column);
                sb.append(String.valueOf(HORIZONTAL).repeat(maxLength));

                if (j == columns.length - 1) {
                    sb.append(BOTTOM_RIGHT);
                } else {
                    sb.append(BOTTOM_LEFT_RIGHT);
                }
            }

        }


        return sb.toString();
    }

    @Contract(pure = true)
    private static String[] toStringArray(Object[] values) {
        String[] strings = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            strings[i] = String.valueOf(values[i]);
        }
        return strings;
    }
}
