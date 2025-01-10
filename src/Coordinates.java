import java.util.Objects;
import java.util.Comparator;

public class Coordinates implements Comparable<Coordinates> {
    private int row;
    private int column;

    public Coordinates(int line, int column) {
        this.row = line;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {
        return row + " " + column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public int compareTo(Coordinates other) {
        // Сортировка по оси X
        if (this.row != other.row) {
            return Integer.compare(this.row, other.row);
        }
        // Если оси X равны, сортировка по оси Y
        return Integer.compare(this.column, other.column);
    }
}


