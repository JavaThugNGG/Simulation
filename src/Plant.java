public abstract class Plant extends Entity {

    public Plant(Coordinates coordinates, String figure) {
        super(coordinates, figure);
    }

    public String toString() {
        return figure;
    }
}