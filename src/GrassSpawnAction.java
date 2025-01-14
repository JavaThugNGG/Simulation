import java.util.List;
import java.util.Map;
import java.util.Random;

public class GrassSpawnAction extends PlantSpawnAction{

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        String figure = "\uD83C\uDF3F";
        Random random = new Random();
        int row = random.nextInt(WORLD_ROWS);
        int column = random.nextInt(WORLD_COLUMNS);

        Coordinates spawnCoordinates = new Coordinates(row,column);
        Grass grass = new Grass(spawnCoordinates, figure);               // создали объект дерева с рандомными координатами
        map.put(spawnCoordinates, grass);                                //положили в мапу
        generatedEntities.add(grass);                                    //положили в список существ
    }
}

