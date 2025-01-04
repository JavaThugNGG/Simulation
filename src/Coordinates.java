public class Coordinates {
    private int horizontal;
    private int vertical;

    public Coordinates(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public void print() {
        System.out.println(horizontal + " " + vertical);
    }
}
