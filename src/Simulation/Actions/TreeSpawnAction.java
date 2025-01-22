package Simulation.Actions;

import Simulation.Entities.Entity;
import Simulation.Entities.Tree;
import Simulation.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.Random;
import Simulation.Simulation;

public class TreeSpawnAction extends PlantSpawnAction {
    private static final String TREE_EMOJI = "\uD83C\uDF33";

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        Random random = new Random();
        int row = random.nextInt(Simulation.WORLD_ROWS);
        int column = random.nextInt(Simulation.WORLD_COLUMNS);
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Tree tree = new Tree(spawnCoordinates, TREE_EMOJI);         // создали объект дерева с рандомными координатами

        map.put(spawnCoordinates, tree);                            //положили в мапу
        generatedEntities.add(tree);                                //положили в список существ
    }
}


