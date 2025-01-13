import java.util.List;
import java.util.Map;
import java.util.Random;

public class PredatorSpawnAction extends CreatureSpawnAction{

    public void perform(Map<Coordinates, Entity> map, List<MoveListener> listeners, List<Entity> generatedEntities) {
        Random random = new Random();
        int row = random.nextInt(10);
        int column = random.nextInt(20);
        String predatorFigure = "\uD83D\uDC3A";


        Coordinates spawnCoordinates = new Coordinates(row, column);
        Predator predator = new Predator(spawnCoordinates, predatorFigure, listeners);
        map.put(spawnCoordinates, predator);
        generatedEntities.add(predator);
    }
}
