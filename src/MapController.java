import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapController implements MoveListener {
    private Map<Coordinates, Entity> map;
    private int width;
    private int height;




    public MapController(Map<Coordinates, Entity> map, int width, int height, List<Entity> generatedEntities, List<MoveListener> listeners) {
        this.map = map;
        this.width = width;
        this.height = height;


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
