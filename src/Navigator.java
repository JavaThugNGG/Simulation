import java.util.List;
import java.util.Map;

public class Navigator {
    private PathFinder pathFinder;
    private Mover mover;

    public Navigator(PathFinder pathFinder, Mover mover) {
        this.pathFinder = pathFinder;
        this.mover = mover;
    }


    public void findAndMove(Map<Coordinates, Entity> map, List<Herbivore> herbivores) {
        for (Herbivore herbivore : herbivores) {
            List<Coordinates> path = pathFinder.findPathToGrass(map, herbivore);
            if (!path.isEmpty()) {
                mover.moveHerbivore(map, path, herbivore);
            }
        }
    }

}
