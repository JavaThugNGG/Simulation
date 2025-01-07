import java.util.Map;

public class MapController implements MoveListener {

    private final Map<Coordinates, Entity> map;

    public MapController(Map<Coordinates, Entity> map) {
        this.map = map;
    }

    @Override
    public void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates) {
        // Обновляем карту
        map.put(newCoordinates, creature);
        map.put(oldCoordinates, new EmptyPlace(oldCoordinates, "⬛"));
    }

    public boolean isPositionEmpty(Coordinates coordinates) {
        return map.get(coordinates) instanceof EmptyPlace;
    }
}
