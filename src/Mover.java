import java.util.List;
import java.util.Map;

public class Mover {
    public void moveHerbivore(Map<Coordinates, Entity> map, List<Coordinates> path, Herbivore herbivore) {
        Coordinates newCoordinates = path.getFirst();
        Coordinates oldCoordinates = herbivore.coordinates;
        herbivore.coordinates = newCoordinates;               //обновили координату существа и запомнили старую
        map.put(newCoordinates, herbivore);
        Entity emptyPlace = new EmptyPlace(oldCoordinates, "⬛");
        map.put(oldCoordinates, emptyPlace);                  //старую "занулили" на карте
    }
}
