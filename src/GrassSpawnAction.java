import java.util.List;
import java.util.Map;
import java.util.Random;

public class GrassSpawnAction extends PlantSpawnAction{

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        String figure = "\uD83C\uDF3F";
        Random random = new Random();
        int row = random.nextInt(10);
        int column = random.nextInt(20);

        Coordinates spawnCoordinates = new Coordinates(row,column);
        Grass grass = new Grass(spawnCoordinates, figure);
        map.put(spawnCoordinates, grass);
        generatedEntities.add(grass);
    }
}

