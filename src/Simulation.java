import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new TreeMap<>();
    private List<Grass> grasses = new ArrayList<>();//мапа для карты
    private List<Herbivore> herbivores = new ArrayList<>();;

    public void startSimulation() {
        MapInitializer mapInitializer = new MapInitializer();
        Renderer renderer = new Renderer();
        mapInitializer.initializeMap(map, grasses, herbivores);
        PathFinder pathFinder = new PathFinder();
        Mover mover = new Mover();
        Navigator coordinator = new Navigator(pathFinder, mover);

        do {
            renderer.printMap(map);
            coordinator.findAndMove(map, herbivores);
        } while (true);
    }
}
