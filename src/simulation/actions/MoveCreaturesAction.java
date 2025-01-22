package simulation.actions;

import simulation.entities.Creature;
import simulation.entities.Entity;
import simulation.entities.Herbivore;
import simulation.Coordinates;
import simulation.PathFinder;
import simulation.entities.Predator;

import java.util.List;

public class MoveCreaturesAction {

    public void perform(List<Entity> generatedEntities, PathFinder pathFinder) {
        for (Entity entity : generatedEntities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                handleCreatureMove(pathFinder, creature);
            }
        }
    }

    private void handleCreatureMove(PathFinder pathFinder, Creature creature) {
        List<Coordinates> path = pathFinder.findPathToVictim(creature);

        if (path.isEmpty()) {
            return;
        }

        if (creature instanceof Predator) {
            movePredator(creature, path);
        } else if (creature instanceof Herbivore) {
            moveHerbivore(creature, path);
        }
    }

    private void movePredator(Creature predator, List<Coordinates> path) {
        for (int i = 0; i < 2; i++) {
            if (path.isEmpty()) {
                break;
            }
            Coordinates newCoordinates = path.removeFirst();
            predator.makeMove(newCoordinates);
        }
    }

    private void moveHerbivore(Creature herbivore, List<Coordinates> path) {
        Coordinates newCoordinates = path.removeFirst();
        herbivore.makeMove(newCoordinates);
    }
}
