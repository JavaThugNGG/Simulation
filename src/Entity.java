public abstract class Entity {
    protected Coordinates coordinates;
    protected String figure;

    public Entity(Coordinates coordinates, String figure) {
        this.coordinates = coordinates;
        this.figure = figure;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getFigure() {
        return figure;
    }

    public abstract void spawn(Coordinates coordinates);

    public void replaceCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
