package Simulation;

import Simulation.Actions.*;
import Simulation.Entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {
    private static final int WORLD_ROWS = 10;
    private static final int WORLD_COLUMNS = 20;

    private final Map<Coordinates, Entity> map = new HashMap<>();
    private final PathFinder pathFinder = new PathFinder();
    private final List<Entity> generatedEntities = new ArrayList<>();
    private final WorldPrinter worldPrinter = new WorldPrinter();
    private final List<MoveListener> listeners = new ArrayList<>();

    private final List<CreatureSpawnAction> creatureInitActions = new ArrayList<>();
    private final List<PlantSpawnAction> plantInitActions = new ArrayList<>();
    private final List<MoveCreaturesAction> turnActions = new ArrayList<>();

    private volatile boolean isEnd = false;              //для паузы

    public void initializeSimulation() {
        creatureInitActions.add(new HerbivoreSpawnAction());
        creatureInitActions.add(new PredatorSpawnAction());
        plantInitActions.add(new GrassSpawnAction());
        plantInitActions.add(new TreeSpawnAction());
        turnActions.add(new MoveCreaturesAction());

        for (PlantSpawnAction action : plantInitActions) {
            action.perform(map, generatedEntities);
        }

        for (CreatureSpawnAction action : creatureInitActions) {
            action.perform(map, generatedEntities, listeners);
        }

        MapController mapController = new MapController(map, generatedEntities);
        listeners.add(mapController);
    }

    public void startSimulation() {
        if (!isEnd) {
            worldPrinter.print(map);
            moveCreations();
            isEnd = isAllHerbivoresDead();
        }
    }

    private void moveCreations() {
        for (MoveCreaturesAction action : turnActions) {
            action.perform(map, generatedEntities, pathFinder);
        }
    }

    private boolean isAllHerbivoresDead() {
        for (Entity entity : map.values()) {
            if (entity instanceof Herbivore) {
                return false;
            }
        }
        return true;
    }

    public void printLast() {                              //для вывода в консоль последнего состояния мапы после окончания цикла симуляции
        worldPrinter.print(map);
    }

    public boolean isEnd() {                              //для получения флага завершения цикла из класса Main
        return isEnd;
    }

    public static int getWORLD_ROWS() {
        return WORLD_ROWS;
    }

    public static int getWORLD_COLUMNS() {
        return WORLD_COLUMNS;
    }
}



