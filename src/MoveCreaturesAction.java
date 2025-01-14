import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveCreaturesAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, PathFinder pathFinder) {

        for (Entity entity : generatedEntities) {
            if (entity instanceof Creature) {
                Creature entity1 = (Creature) entity;
                if (entity1 instanceof Predator) {
                    List<Coordinates> path = pathFinder.findPathToVictim(map, entity1);
                    System.out.println(entity1);
                    System.out.println("Предатор" + path);

                    if (path.isEmpty()) {     //если пути нет, то двигаться не надо
                        continue;
                    }

                    for (int i = 0; i < 2; i++) {
                        if (path.isEmpty()) {
                            break; // прекращаем цикл, если путь пуст
                        }
                        Coordinates newCoordinates = path.removeFirst();
                        entity1.makeMove(newCoordinates);
                    }
                } else if (entity1 instanceof Creature) {
                    if (entity instanceof Herbivore) {
                        List<Coordinates> path = pathFinder.findPathToVictim(map, entity1);
                        System.out.println(entity1);
                        System.out.println("Хербивор" + path);

                        if (path.isEmpty()) {     //если пути нет, то двигаться не надо
                            continue;
                        }

                        Coordinates newCoordinates = path.removeFirst();
                        entity1.makeMove(newCoordinates);
                    }
                }
            }
        }
    }
}

