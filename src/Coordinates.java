import java.awt.event.ComponentListener;

public class Coordinates implements Comparable<Coordinates> {
    private int horizontal;
    private int vertical;

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
