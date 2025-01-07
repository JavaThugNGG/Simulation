import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new TreeMap<>();
    private List<Grass> grasses = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();;
    private boolean isWin = false;

    private final List<MoveListener> listeners = new ArrayList<>();

    MapController mapController = new MapController(map);
    MoveController moveController = new MoveController();

    public void startSimulation() {
        MapController mapController = new MapController(map);
        MapInitializer mapInitializer = new MapInitializer();
        Renderer renderer = new Renderer();
        mapInitializer.initializeMap(map, grasses, creatures, listeners);
        PathFinder pathFinder = new PathFinder();
        MoveController mover = new MoveController();
        Navigator coordinator = new Navigator(pathFinder, mover);
        listeners.add(mapController);

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


    public void addMoveListener(MoveListener listener) {
        listeners.add(listener);
    }

    public void removeMoveListener(MoveListener listener) {
        listeners.remove(listener);
    }

}
