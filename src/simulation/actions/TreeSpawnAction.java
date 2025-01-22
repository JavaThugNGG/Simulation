package simulation.actions;

import simulation.entities.Entity;
import simulation.entities.Tree;
import simulation.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.Random;
import simulation.Simulation;

public class TreeSpawnAction extends PlantSpawnAction {
    private static final String TREE_EMOJI = "\uD83C\uDF33";

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        Random random = new Random();
        int row = random.nextInt(Simulation.WORLD_ROWS);
        int column = random.nextInt(Simulation.WORLD_COLUMNS);
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Tree tree = new Tree(spawnCoordinates, TREE_EMOJI);

        map.put(spawnCoordinates, tree);
        generatedEntities.add(tree);
    }
}


