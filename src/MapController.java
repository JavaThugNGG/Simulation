import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapController implements MoveListener {
    private Map<Coordinates, Entity> map;
    private final int WORLD_ROWS = Simulation.getWORLD_ROWS();
    private final int WORLD_COLUMNS = Simulation.getWORLD_COLUMNS();




    public MapController(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        this.map = map;

        for (Entity entity : generatedEntities) {     //заполнение мапы
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
