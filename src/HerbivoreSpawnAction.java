import java.util.List;
import java.util.Map;
import java.util.Random;

public class HerbivoreSpawnAction extends CreatureSpawnAction {

    public void perform(Map<Coordinates, Entity> map, List<MoveListener> listeners, List<Entity> generatedEntities) {
        Random random = new Random();
        int row = random.nextInt(10);
        int column = random.nextInt(20);
        String herbivoreFigure = "\uD83D\uDC30";

        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, herbivoreFigure, listeners);
        map.put(spawnCoordinates, herbivore);
        generatedEntities.add(herbivore);
    }
}
