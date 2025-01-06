import java.util.List;
import java.util.Map;

public class Navigator {
    private PathFinder pathFinder;
    private Mover mover;

    public Navigator(PathFinder pathFinder, Mover mover) {
        this.pathFinder = pathFinder;
        this.mover = mover;
    }


    public void findPathAndMove(Map<Coordinates, Entity> map, List<Creature> creatures) {
        for (Creature creature : creatures) {
            List<Coordinates> path = pathFinder.findPathToVictim(map, creature);
            if (!path.isEmpty()) {
                System.out.println(creature.figure);
                System.out.println(path);
                mover.moveCreature(map, path, creature);
            }
        }
    }

}
