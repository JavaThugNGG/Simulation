public abstract class Entity {
    protected Coordinates coordinates;
    protected String figure;
    protected boolean isEdibleForHerbivore;

    public Entity(Coordinates coordinates, String figure) {
        this.coordinates = coordinates;
        this.figure = figure;;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
