import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Navigator {
    private PathFinder pathFinder;
    private MoveController moveController;

    public Navigator(PathFinder pathFinder, MoveController moveController) {
        this.pathFinder = pathFinder;
        this.moveController = moveController;
    }

    public void findPathAndMove(Map<Coordinates, Entity> map) {
        List<Creature> creatures = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            if (entry.getValue() instanceof Creature) {
                Creature creature = (Creature) entry.getValue();
                creatures.add(creature);
            }
        }

        for (Creature creature : creatures) {
            List<Coordinates> path = pathFinder.findPathToVictim(map, creature);
            if (!path.isEmpty()) {
                moveController.moveCreature(path, creature);
                System.out.println(creature + " " + path);
            }
        }
    }
}
