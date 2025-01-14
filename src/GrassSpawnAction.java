import java.util.List;
import java.util.Map;
import java.util.Random;

public class GrassSpawnAction extends PlantSpawnAction{

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, int worldRows, int worldColumns) {
        String figure = "\uD83C\uDF3F";
        Random random = new Random();
        int row = random.nextInt(worldRows);
        int column = random.nextInt(worldColumns);

        Coordinates spawnCoordinates = new Coordinates(row,column);
        Grass grass = new Grass(spawnCoordinates, figure);               // создали объект дерева с рандомными координатами
        map.put(spawnCoordinates, grass);                                //положили в мапу
        generatedEntities.add(grass);                                    //положили в список существ
    }
}

