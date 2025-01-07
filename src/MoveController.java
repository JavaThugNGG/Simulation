import java.util.List;

public class MoveController {

    public void moveCreature(List<Coordinates> path, Creature creature) {
        if (path.isEmpty()) {
            return;
        }

        int steps = creature instanceof Herbivore ? 1 : 2;

        for (int i = 0; i < steps && !path.isEmpty(); i++) {
            Coordinates newCoordinates = path.removeFirst();
            creature.makeMove(newCoordinates);
        }
    }
}
