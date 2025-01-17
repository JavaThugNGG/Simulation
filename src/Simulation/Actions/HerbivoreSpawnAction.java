package Simulation.Actions;

import Simulation.Coordinates;
import Simulation.Entities.Entity;
import Simulation.Entities.Herbivore;
import Simulation.MoveListener;


import java.util.List;
import java.util.Map;
import java.util.Random;

public class HerbivoreSpawnAction extends CreatureSpawnAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners) {
        Random random = new Random();
        int row = random.nextInt(WORLD_ROWS);
        int column = random.nextInt(WORLD_COLUMNS);
        String herbivoreFigure = "\uD83D\uDC30";

        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, herbivoreFigure, listeners);    //создали объект с рандомными координатами
        map.put(spawnCoordinates, herbivore);                                                 //положили его в мапу
        generatedEntities.add(herbivore);                                                     //положили его в список существ
    }
}
