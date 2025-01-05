import java.awt.event.ComponentListener;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {
    private int horizontal;
    private int vertical;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return horizontal == that.horizontal && vertical == that.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    public Coordinates(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int compareTo(Coordinates other) {
        // Сортировка по оси X
        if (this.horizontal != other.horizontal) {
            return Integer.compare(this.horizontal, other.horizontal);
        }
        // Если оси X равны, сортировка по оси Y
        return Integer.compare(this.vertical, other.vertical);
    }

    public String toString() {
        return horizontal + " " + vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }
}
