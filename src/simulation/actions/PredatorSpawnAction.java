package simulation.actions;

import simulation.entities.Entity;
import simulation.Coordinates;
import simulation.MoveListener;
import simulation.entities.Predator;

import java.util.List;
import java.util.Map;
import java.util.Random;
import simulation.Simulation;

public class PredatorSpawnAction extends CreatureSpawnAction {
    private static final String PREDATOR_EMOJI = "\uD83D\uDC3A";

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners) {
        Random random = new Random();
        int row = random.nextInt(Simulation.WORLD_ROWS);
        int column = random.nextInt(Simulation.WORLD_COLUMNS);
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Predator predator = new Predator(spawnCoordinates, PREDATOR_EMOJI, listeners);
        map.put(spawnCoordinates, predator);
        generatedEntities.add(predator);
    }
}
