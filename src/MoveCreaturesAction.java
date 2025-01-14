import java.util.List;
import java.util.Map;

public class MoveCreaturesAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, PathFinder pathFinder) {

        for (Entity entity : generatedEntities) {

            if (entity instanceof Creature) {                                              //если сущность это существо
                Creature creature = (Creature) entity;                                     //сделали downcasting к классу Creature (потому что метод makeMove у Creature только есть)
                List<Coordinates> path = pathFinder.findPathToVictim(map, creature);       //рассчитали путь
                if (path.isEmpty()) {                                                      //если пути нет, то двигаться не надо
                    continue;
                }

                if (creature instanceof Predator) {

                    for (int i = 0; i < 2; i++) {                                             //хищник делает 2 шага за кадр
                        if (path.isEmpty()) {
                            break; // прекращаем цикл, если второго шага не требуется уже
                        }
                        Coordinates newCoordinates = path.removeFirst();                      //из списка убрали координату на которую только что сходили (обновили)
                        creature.makeMove(newCoordinates);
                    }
                } else if (creature instanceof Herbivore) {
                    Coordinates newCoordinates = path.removeFirst();
                    creature.makeMove(newCoordinates);
                }
            }
        }
    }
}

