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

    private volatile boolean isPaused = false;
    private volatile boolean isRunning = false;
    private volatile boolean isEnd = false;

    private final Object pauseLock = new Object();

    public void initializeSimulation() {
        // Инициализация объектов
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
        isRunning = true;
        isEnd = false;

        Thread simulationThread = new Thread(() -> {
            while (!isEnd) {
                synchronized (pauseLock) {
                    while (isPaused) {
                        try {
                            pauseLock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }

                worldPrinter.print(map);
                moveCreations();
                isEnd = isHerbivoresDead();

                try {
                    Thread.sleep(1000); // Задержка для визуализации
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            isRunning = false;
        });

        simulationThread.start();
    }

    public void pauseSimulation() {
        synchronized (pauseLock) {
            isPaused = true;
        }
    }

    public void resumeSimulation() {
        synchronized (pauseLock) {
            isPaused = false;
            pauseLock.notifyAll();
        }
    }

    private void moveCreations() {
        for (MoveCreaturesAction action : turnActions) {
            action.perform(map, generatedEntities, pathFinder);
        }
    }

    private boolean isHerbivoresDead() {
        for (Entity entity : map.values()) {
            if (entity instanceof Herbivore) {
                return false;
            }
        }
        return true;
    }

    public static int getWORLD_ROWS() {
        return WORLD_ROWS;
    }

    public static int getWORLD_COLUMNS() {
        return WORLD_COLUMNS;
    }
}



