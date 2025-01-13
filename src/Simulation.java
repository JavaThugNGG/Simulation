import java.nio.file.Path;
import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new HashMap<>();
    private List<MoveListener> listeners = new ArrayList<>();    //список слушателей (паттерн observer)
    private List<Entity> generatedEntities= new ArrayList<>();
    private PathFinder pathFinder = new PathFinder();

    private boolean isEnd;

    private List<CreatureSpawnAction> creatureInitActions = new ArrayList<>();
    private List<PlantSpawnAction> plantInitActions = new ArrayList<>();;

    private List<MoveCreaturesAction> turnActions = new ArrayList<>();;



    public void initializeSimulation() {
        MapController mapController = new MapController(map, 10, 20, generatedEntities, listeners);
        Renderer renderer = new Renderer();
        listeners.add(mapController);
        PathFinder pathFinder = new PathFinder();
        runSimulation(renderer);
    }

    private void runSimulation(Renderer renderer) {

        creatureInitActions.add(new HerbivoreSpawnAction());
        creatureInitActions.add(new PredatorSpawnAction());

        plantInitActions.add(new GrassSpawnAction());
        plantInitActions.add(new TreeSpawnAction());

        turnActions.add(new MoveCreaturesAction());

        for (PlantSpawnAction action : plantInitActions) {
            action.perform(map, generatedEntities);
        }

        for (CreatureSpawnAction action : creatureInitActions) {
            action.perform(map, listeners, generatedEntities);
        }





        do {
            isEnd = true;
            for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
                if (entry.getValue() instanceof Herbivore) {
                    isEnd = false;
                    break;
                }
            }


            renderer.printMap(map);

            for (MoveCreaturesAction action : turnActions) {
                action.perform(map, generatedEntities, pathFinder);
            }



        } while (!isEnd);
    }
}


