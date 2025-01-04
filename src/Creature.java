public abstract class Creature extends Entity{

    public Creature(int horizontalCoordinate, int verticalCoordinate, String figure) {
        super(horizontalCoordinate, verticalCoordinate, figure);  //проверка что hp не больше 100
    }
}
