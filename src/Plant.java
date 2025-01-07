public abstract class Plant extends Entity {
    protected boolean isEdible;

    public Plant(Coordinates coordinates, String figure) {
        super(coordinates, figure);
    }

    public void spawn(Coordinates coordinates) {

    }

    public String toString() {
        return figure;
    }

    public boolean isEdible() {
        return isEdible;
    }
}
