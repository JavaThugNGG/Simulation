import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Herbivore extends Creature{
    private final List<MoveListener> listeners = new ArrayList<>();

    public Herbivore(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure, listeners);  //проверка что hp не больше 100
    }

    public String toString() {
        return figure;
    }



}
