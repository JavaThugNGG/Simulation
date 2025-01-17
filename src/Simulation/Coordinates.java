package Simulation;

import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {
    private final int row;
    private final int column;

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
    public boolean equals(Object o) {    //для HashMap (потому что ключ - объект)
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
    public int compareTo(Coordinates other) {                    //для HashMap -> TreeMap (потому что ключи у нас объекты)
        if (this.row != other.row) {
            return Integer.compare(this.row, other.row);
        }
        return Integer.compare(this.column, other.column);
    }
}


