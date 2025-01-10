import java.util.List;
import java.util.Map;

public class Navigator {
    private PathFinder pathFinder;
    private MoveController mover;

    public Navigator(PathFinder pathFinder, MoveController mover) {
        this.pathFinder = pathFinder;
        this.mover = mover;
    }

    public void findPathAndMove(Map<Coordinates, Entity> map, List<Creature> creatures) {
        for (Creature creature : creatures) {
            List<Coordinates> path = pathFinder.findPathToVictim(map, creature);
            if (!path.isEmpty()) {
                mover.moveCreature(path, creature);
            }
        }
    }
}
