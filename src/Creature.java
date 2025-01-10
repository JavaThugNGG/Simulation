import java.util.List;

public abstract class Creature extends Entity {
    private List<MoveListener> listeners;

    public Creature(Coordinates coordinates, String figure,  List<MoveListener> listeners) {
        super(coordinates, figure);  //проверка что hp не больше 100
        this.listeners = listeners;
    }

    public void makeMove(Coordinates newCoordinates) {
        Coordinates oldCoordinates = this.coordinates;        //обновление своих координат
        this.coordinates = newCoordinates;

        for (MoveListener listener : listeners) {                           //уведомляем слушателей о перемещении
            listener.onMove(this, oldCoordinates, newCoordinates);
        }
    }
}
