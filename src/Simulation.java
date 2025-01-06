import java.util.*;


public class Simulation {
    private Map<Coordinates, Entity> map = new TreeMap<>();            //мапа для карты
    private List<Herbivore> herbivores = new ArrayList<>();;
    private List<Grass> grasses = new ArrayList<>();

    public Simulation() {};

    public void startSimulation() {
        MapInitializer mapInitializer = new MapInitializer();
        mapInitializer.initializeMap(map, grasses, herbivores);
        do {;
            printMap();
            eatGrassIfAble();
        } while (true);
    }


    public void eatGrassIfAble() {
        for (Herbivore herbivore : herbivores) {
            List<Coordinates> path = findPath(herbivore);
            if (!path.isEmpty()) {
                moveHerbivore(path, herbivore);
            }
        }
    }

    public List<Coordinates> findPath(Herbivore herbivore) {
        PathFinder pathFinder = new PathFinder();
        return pathFinder.findPathToGrass(map, herbivore.getCoordinates());
    }


    public void moveHerbivore(List<Coordinates> path, Herbivore herbivore) {
            Coordinates newCoordinates = path.getFirst();
            Coordinates oldCoordinates = herbivore.coordinates;
            herbivore.coordinates = newCoordinates;
            map.put(newCoordinates, herbivore);
            Entity emptyPlace = new EmptyPlace(oldCoordinates, "⬛");
            map.put(oldCoordinates, emptyPlace);
    }


    public void printMap() {
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            System.out.print(entry.getValue());

            if (entry.getKey().getColumn() == 19) {
                System.out.print("\n");
            }
        }
        System.out.println();
    }
}
