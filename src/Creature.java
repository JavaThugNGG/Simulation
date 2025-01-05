public abstract class Creature extends Entity{

    public Creature(Coordinates coordinates, String figure) {
        super(coordinates, figure);  //проверка что hp не больше 100
    }
}
