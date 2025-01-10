import java.util.List;

public class MoveController {
    public void moveCreature(List<Coordinates> path, Creature creature) {
        if (path.isEmpty()) {
            return;
        }

        int steps = determineSteps(creature);

        for (int i = 0; i < steps; i++) {
            if (path.isEmpty()) {
                break; // Прекращаем цикл, если путь пуст
            }
            Coordinates newCoordinates = path.removeFirst();
            creature.makeMove(newCoordinates);
        }
    }

    private int determineSteps(Creature creature) {
        if (creature instanceof Herbivore) {
            return 1;
        } else {
            return 2;
        }
    }
}
