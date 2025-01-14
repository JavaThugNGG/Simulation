import java.nio.file.Path;
import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new HashMap<>();
    private List<MoveListener> listeners = new ArrayList<>();    //список слушателей (паттерн observer)
    private PathFinder pathFinder = new PathFinder();
    private List<Entity> generatedEntities= new ArrayList<>();

    private List<CreatureSpawnAction> creatureInitActions = new ArrayList<>();
    private List<PlantSpawnAction> plantInitActions = new ArrayList<>();;
    private List<MoveCreaturesAction> turnActions = new ArrayList<>();;

    private boolean isEnd;


    public void initializeSimulation() {
        creatureInitActions.add(new HerbivoreSpawnAction());
        creatureInitActions.add(new PredatorSpawnAction());

        plantInitActions.add(new GrassSpawnAction());
        plantInitActions.add(new TreeSpawnAction());

        turnActions.add(new MoveCreaturesAction());

        for (PlantSpawnAction action : plantInitActions) {
            action.perform(map, generatedEntities);
        }

        for (CreatureSpawnAction action : creatureInitActions) {
            action.perform(map, generatedEntities, listeners);
        }

        MapController mapController = new MapController(map, 10, 20, generatedEntities, listeners);
        listeners.add(mapController);
        Renderer renderer = new Renderer();
        runSimulation(renderer);
    }

    private void runSimulation(Renderer renderer) {
        do {
            renderer.printMap(map);
            isEnd = isHerbivoresAlive();
            moveCreations();

        } while (!isEnd);
    }

    private void moveCreations() {
        for (MoveCreaturesAction action : turnActions) {
            action.perform(map, generatedEntities, pathFinder);
        }
    }

    private boolean isHerbivoresAlive() {
        boolean isEnd = true;
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            if (entry.getValue() instanceof Herbivore) {
                isEnd = false;
                break;
            }
        }
        return isEnd;
    }
}


