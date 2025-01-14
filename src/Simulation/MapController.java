package Simulation;

import Simulation.Entities.Creature;
import Simulation.Entities.Entity;

import java.util.List;
import java.util.Map;

public class MapController implements MoveListener {
    private Map<Coordinates, Entity> map;

    public MapController(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        this.map = map;
        fillMap(generatedEntities);
    }

    private void fillMap(List<Entity> generatedEntities) {
        for (Entity entity : generatedEntities) {                       //заполнение мапы существами
            Coordinates entityCoordinates = entity.getCoordinates();
            map.put(entityCoordinates, entity);
        }
    }

    @Override
    public void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates) {   //когда существо меняет координаты -> уведомляет мапу, и она обновляется
        map.put(newCoordinates, creature);
        map.remove(oldCoordinates);
    }

}
