import java.util.List;

public class Predator extends Creature {
    //пока без силы удара

    public Predator(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure, listeners);  //проверка что hp не больше 100
    }


    public String toString() {
        return figure;
    }

    public void makeMove() {
        //
    }
}
