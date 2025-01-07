import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

public abstract class Creature extends Entity{
    private List<MoveListener> listeners = new ArrayList<>();

    public Creature(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure);  //проверка что hp не больше 100
        this.listeners = listeners;
    }

    public void spawn(Coordinates coordinates) {

    }

    public void makeMove(Coordinates newCoordinates) {
        Coordinates oldCoordinates = this.coordinates;
        this.coordinates = newCoordinates;

        // Уведомляем всех слушателей о перемещении
        for (MoveListener listener : listeners) {
            listener.onMove(this, oldCoordinates, newCoordinates);
        }
    }


}
