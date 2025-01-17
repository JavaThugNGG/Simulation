package Simulation.Actions;

import Simulation.Entities.Entity;
import Simulation.Coordinates;
import Simulation.MoveListener;
import Simulation.Entities.Predator;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class PredatorSpawnAction extends CreatureSpawnAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners) {
        Random random = new Random();
        int row = random.nextInt(WORLD_ROWS);
        int column = random.nextInt(WORLD_COLUMNS);
        String predatorFigure = "\uD83D\uDC3A";

        Coordinates spawnCoordinates = new Coordinates(row, column);
        Predator predator = new Predator(spawnCoordinates, predatorFigure, listeners);    //создали объект с рандомными координатами
        map.put(spawnCoordinates, predator);                                              //положили его в мапу
        generatedEntities.add(predator);                                                  //положили его в список существ
    }
}
