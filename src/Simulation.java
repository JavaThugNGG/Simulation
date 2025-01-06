import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new TreeMap<>();
    private List<Grass> grasses = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();;
    private boolean isWin = false;

    public void startSimulation() {
        MapInitializer mapInitializer = new MapInitializer();
        Renderer renderer = new Renderer();
        mapInitializer.initializeMap(map, grasses, creatures);
        PathFinder pathFinder = new PathFinder();
        Mover mover = new Mover();
        Navigator coordinator = new Navigator(pathFinder, mover);

        do {
            isWin = true; // Предполагаем, что победа, пока не докажем обратное

            for (Entity entity : map.values()) {
                if (entity instanceof Herbivore) {
                    isWin = false; // Если нашли травоядное, победа отменяется
                    break; // Достаточно найти одно травоядное, чтобы выйти из проверки
                }
            }

            renderer.printMap(map);
            coordinator.findPathAndMove(map, creatures);
        } while (!isWin); // Продолжаем, пока не наступила победа
    }

}
