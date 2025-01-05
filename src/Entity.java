public abstract class Entity {
    protected Coordinates coordinates;
    protected String figure;

    public Entity(Coordinates coordinates, String figure) {
        this.coordinates = coordinates;
        this.figure = figure;
    }


    public abstract void spawn(Coordinates coordinates);

    Coordinates getCoordinates() {
        return coordinates;
    }

    String getFigure() {
        return figure;
    }
}
