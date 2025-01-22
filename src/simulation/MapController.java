package simulation;

import simulation.entities.Creature;
import simulation.entities.Entity;

import java.util.List;
import java.util.Map;

public class MapController implements MoveListener {
    private final Map<Coordinates, Entity> map;

    public MapController(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        this.map = map;
        fillMap(generatedEntities);
    }

    private void fillMap(List<Entity> generatedEntities) {
        for (Entity entity : generatedEntities) {
            Coordinates entityCoordinates = entity.getCoordinates();
            map.put(entityCoordinates, entity);
        }
    }

    @Override
    public void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates) {
        map.put(newCoordinates, creature);
        map.remove(oldCoordinates);
    }

}
