package simulation;

import simulation.entities.Entity;

import java.util.Map;

public class WorldPrinter {

    public void print(Map<Coordinates, Entity> inputMap) {
        System.out.println();
        for (int i = 0; i < Simulation.WORLD_ROWS; i++) {
            for (int j = 0; j < Simulation.WORLD_COLUMNS; j++) {
                Coordinates coordinate = new Coordinates(i, j);

                if (inputMap.containsKey(coordinate)) {
                    Entity entity = inputMap.get(coordinate);
                    System.out.print(entity);
                } else {
                    System.out.print("⬛");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
