public class EmptyPlace extends Entity{

    public EmptyPlace(Coordinates coordinates, String figure) {
        super(coordinates, figure);
    }

    public String toString() {
        return figure;
    }

    public void spawn(Coordinates coordinates) {

    }
}
