import java.util.List;
import java.util.Map;

public class Mover {


    public void moveCreature(Map<Coordinates, Entity> map, List<Coordinates> path, Creature creature) {
        if (creature instanceof Herbivore) {
            makeStep( map, path, creature);

        } else if (creature instanceof Predator) {
            makeStep( map, path, creature);
            makeStep( map, path, creature);
        }
    }

    private void makeStep(Map<Coordinates, Entity> map, List<Coordinates> path, Creature creature) {
        if (path.isEmpty()) {
            return;
        }
        Coordinates newCoordinates = path.getFirst();
        Coordinates oldCoordinates = creature.coordinates;
        creature.coordinates = newCoordinates;               //обновили координату существа и запомнили старую
        map.put(newCoordinates, creature);
        Entity emptyPlace = new EmptyPlace(oldCoordinates, "⬛");
        map.put(oldCoordinates, emptyPlace);        //старую "занулили" на карте
        path.remove(newCoordinates);
    }
}
