public abstract class Entity {
    protected int horizontalCoordinate;
    protected int verticalCoordinate;
    protected String figure;

    public Entity(int horizontalCoordinate, int verticalCoordinate, String figure) {
        this.horizontalCoordinate = horizontalCoordinate;
        this.verticalCoordinate = verticalCoordinate;
        this.figure = figure;
    }
}
