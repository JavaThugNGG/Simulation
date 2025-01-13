import java.util.List;
import java.util.Map;
import java.util.Random;

public class TreeSpawnAction extends PlantSpawnAction{

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        Random random = new Random();
        int row = random.nextInt(10);
        int column = random.nextInt(20);
        String treeFigure = "\uD83C\uDF33";

        Coordinates spawnCoordinates = new Coordinates(row,column);
        Tree tree = new Tree(spawnCoordinates, treeFigure);
        map.put(spawnCoordinates, tree);
        generatedEntities.add(tree);
    }
}


