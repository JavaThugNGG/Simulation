import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new HashMap<>();
    private List<MoveListener> listeners = new ArrayList<>();    //список слушателей (паттерн observer)

    private boolean isEnd;



    public void initializeSimulation() {
        MapController mapController = new MapController(map, 10, 20, listeners);
        Renderer renderer = new Renderer();
        listeners.add(mapController);
        PathFinder pathFinder = new PathFinder();
        MoveController moveController = new MoveController();
        Navigator navigator = new Navigator(pathFinder, moveController);
        runSimulation(renderer, navigator);
    }

    private void runSimulation(Renderer renderer, Navigator navigator) {
        do {
            isEnd = true;
            for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
                if (entry.getValue() instanceof Herbivore) {
                    isEnd = false;
                    break;
                }
            }

            renderer.printMap(map);
            navigator.findPathAndMove(map);    //обновляем пути у всех существ
        } while (!isEnd);
    }
}


