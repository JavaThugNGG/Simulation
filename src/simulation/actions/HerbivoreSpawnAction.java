package simulation.actions;

import simulation.Coordinates;
import simulation.entities.Entity;
import simulation.entities.Herbivore;
import simulation.MoveListener;
import simulation.Simulation;


import java.util.List;
import java.util.Map;
import java.util.Random;

public class HerbivoreSpawnAction extends CreatureSpawnAction {
    private static final String HERBIVORE_EMOJI = "\uD83D\uDC30";

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners) {
        Random random = new Random();
        int row = random.nextInt(Simulation.WORLD_ROWS);
        int column = random.nextInt(Simulation.WORLD_COLUMNS);

        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, HERBIVORE_EMOJI, listeners);
        map.put(spawnCoordinates, herbivore);
        generatedEntities.add(herbivore);
    }
}
