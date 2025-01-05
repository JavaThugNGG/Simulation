import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {
    private int line;
    private int column;

    public Coordinates(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {
        return line + " " + column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return line == that.line && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }

    @Override
    public int compareTo(Coordinates other) {
        // Сортировка по оси X
        if (this.line != other.line) {
            return Integer.compare(this.line, other.line);
        }
        // Если оси X равны, сортировка по оси Y
        return Integer.compare(this.column, other.column);
    }
}
