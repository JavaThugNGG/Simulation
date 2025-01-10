import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new TreeMap<>();
    private List<MoveListener> listeners = new ArrayList<>();    //список слушателей
    private List<Creature> creatures = new ArrayList<>();
    private List<Plant> plantes = new ArrayList<>();
    private boolean isEnd;


    public void initializeSimulation() {
        MapController mapController = new MapController(map);
        Renderer renderer = new Renderer();
        mapController.initializeMap(map, plantes, creatures, listeners);
        PathFinder pathFinder = new PathFinder();
        MoveController mover = new MoveController();
        Navigator navigator = new Navigator(pathFinder, mover);
        listeners.add(mapController);

        runSimulation(renderer, navigator);
    }

    private void runSimulation(Renderer renderer, Navigator navigator) {
        do {
            isEnd = true;
            for (Entity entity : map.values()) {
                if (entity instanceof Herbivore) {
                    isEnd = false;
                    break;
                }
            }

            renderer.printMap(map);
            navigator.findPathAndMove(map, creatures);    //обновляем пути у всех существ
        } while (!isEnd);
    }
}


