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
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            List<Coordinates> path = pathFinder.findPathToVictim(map);
            if (!path.isEmpty()) {
                moveController.moveCreature(path, creature);
            }
        }
    }
}
