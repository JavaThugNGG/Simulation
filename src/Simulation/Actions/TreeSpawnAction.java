package Simulation.Actions;

import Simulation.Entities.Entity;
import Simulation.Entities.Tree;
import Simulation.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class TreeSpawnAction extends PlantSpawnAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities) {
        Random random = new Random();
        int row = random.nextInt(WORLD_ROWS);
        int column = random.nextInt(WORLD_COLUMNS);
        String treeFigure = "\uD83C\uDF33";

        Coordinates spawnCoordinates = new Coordinates(row,column);
        Tree tree = new Tree(spawnCoordinates, treeFigure);         // создали объект дерева с рандомными координатами
        map.put(spawnCoordinates, tree);                            //положили в мапу
        generatedEntities.add(tree);                                //положили в список существ
    }
}


