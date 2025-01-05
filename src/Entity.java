public abstract class Entity {
    protected Coordinates coordinates;
    protected String figure;

    public Entity(Coordinates coordinates, String figure) {
        this.coordinates = coordinates;
        this.figure = figure;
    }
}
