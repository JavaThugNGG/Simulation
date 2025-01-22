package Simulation.Actions;

import Simulation.Entities.Entity;
import Simulation.Coordinates;
import Simulation.MoveListener;
import Simulation.Entities.Predator;

import java.util.List;
import java.util.Map;
import java.util.Random;
import Simulation.Simulation;

public class PredatorSpawnAction extends CreatureSpawnAction {
    private static final String PREDATOR_EMOJI = "\uD83D\uDC3A";

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners) {
        Random random = new Random();
        int row = random.nextInt(Simulation.WORLD_ROWS);
        int column = random.nextInt(Simulation.WORLD_COLUMNS);

        Coordinates spawnCoordinates = new Coordinates(row, column);
        Predator predator = new Predator(spawnCoordinates, PREDATOR_EMOJI, listeners);    //создали объект с рандомными координатами
        map.put(spawnCoordinates, predator);                                              //положили его в мапу
        generatedEntities.add(predator);                                                  //положили его в список существ
    }
}
