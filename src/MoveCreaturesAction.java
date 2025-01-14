import java.util.List;
import java.util.Map;

public class MoveCreaturesAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, PathFinder pathFinder) {
        for (Entity entity : generatedEntities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;                   // downcasting до класса Creature (метод движения там)
                handleCreatureMove(map, pathFinder, creature);
            }
        }
    }

    private void handleCreatureMove(Map<Coordinates, Entity> map, PathFinder pathFinder, Creature creature) {
        List<Coordinates> path = pathFinder.findPathToVictim(map, creature);           // рассчитать путь к цели
        if (path.isEmpty()) {
            return;                                                                    // если пути нет, существо не движется
        }

        if (creature instanceof Predator) {                                             //кол-во шагов зависит от типа существа
            movePredator(creature, path);
        } else if (creature instanceof Herbivore) {
            moveHerbivore(creature, path);
        }
    }

    private void movePredator(Creature predator, List<Coordinates> path) {
        for (int i = 0; i < 2; i++) {                                                       //хищник делает 2 хода за кадр
            if (path.isEmpty()) {
                break;
            }
            Coordinates newCoordinates = path.removeFirst();                               // обновляем путь (удаляем координату, куда уже сходили)
            predator.makeMove(newCoordinates);
        }
    }

    private void moveHerbivore(Creature herbivore, List<Coordinates> path) {
        Coordinates newCoordinates = path.removeFirst();                                   // обновляем путь (удаляем координату, куда уже сходили)
        herbivore.makeMove(newCoordinates);
    }
}
