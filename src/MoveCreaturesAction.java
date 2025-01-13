import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveCreaturesAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, PathFinder pathFinder) {

        for (Entity entity1 : generatedEntities) {

            if (entity1 instanceof Creature) {
                Creature entity = (Creature) entity1;
                if (entity instanceof Herbivore) {
                    List<Coordinates> path = pathFinder.findPathToVictim(map, entity);

                    if (path.isEmpty()) {     //если пути нет, то двигаться не надо
                        return;
                    }

                    Coordinates newCoordinates = path.removeFirst();
                    entity.makeMove(newCoordinates);
                }
            } else if (entity1 instanceof Creature) {
                Creature entity = (Creature) entity1;
                if (entity instanceof Predator) {
                    List<Coordinates> path = pathFinder.findPathToVictim(map, entity);

                    if (path.isEmpty()) {     //если пути нет, то двигаться не надо
                        return;
                    }

                    for (int i = 0; i < 2; i++) {
                        if (path.isEmpty()) {
                            break; // прекращаем цикл, если путь пуст
                        }
                        Coordinates newCoordinates = path.removeFirst();
                        entity.makeMove(newCoordinates);
                    }
                }
            }
        }
    }
}

