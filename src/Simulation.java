import java.util.*;


public class Simulation {
    private final int WORLD_ROWS = 10;
    private final int WORLD_COLUMNS = 20;

    private Map<Coordinates, Entity> map = new HashMap<>();
    private PathFinder pathFinder = new PathFinder();
    private List<Entity> generatedEntities= new ArrayList<>();
    private WorldPrinter worldPrinter = new WorldPrinter(WORLD_ROWS, WORLD_COLUMNS);
    private List<MoveListener> listeners = new ArrayList<>();    //список слушателей (паттерн observer)

    private List<CreatureSpawnAction> creatureInitActions = new ArrayList<>();
    private List<PlantSpawnAction> plantInitActions = new ArrayList<>();;
    private List<MoveCreaturesAction> turnActions = new ArrayList<>();;


    public void runSimulation() {
        boolean isEnd;
        initializeSimulation();
        worldPrinter.print(map);
        do {
            moveCreations();
            isEnd = isHerbivoresAlive();
            worldPrinter.print(map);

        } while (!isEnd);
    }


    private void initializeSimulation() {
        creatureInitActions.add(new HerbivoreSpawnAction());
        creatureInitActions.add(new PredatorSpawnAction());

        plantInitActions.add(new GrassSpawnAction());
        plantInitActions.add(new TreeSpawnAction());

        turnActions.add(new MoveCreaturesAction());

        for (PlantSpawnAction action : plantInitActions) {
            action.perform(map, generatedEntities, WORLD_ROWS, WORLD_COLUMNS);
        }

        for (CreatureSpawnAction action : creatureInitActions) {
            action.perform(map, generatedEntities, listeners);
        }

        MapController mapController = new MapController(map, WORLD_ROWS, WORLD_COLUMNS, generatedEntities, listeners);
        listeners.add(mapController);
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


