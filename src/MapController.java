import java.util.List;
import java.util.Map;

public class MapController implements MoveListener {
    private final Map<Coordinates, Entity> map;
    private final String GRASSFIGURE = "\uD83C\uDF3F";
    private final String TREEFIGURE = "\uD83C\uDF33";
    private final String HERBIVOREFIGURE = "\uD83D\uDC30";
    private final String PREDATIRFIGURE = "\uD83D\uDC3A";


    public MapController(Map<Coordinates, Entity> map) {
        this.map = map;
    }

    @Override
    public void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates) {
        map.put(newCoordinates, creature);
        map.put(oldCoordinates, new EmptyPlace(oldCoordinates, "⬛"));
    }

    public void initializeMap(Map<Coordinates, Entity> map, List<Plant> plants, List<Creature> creatures, List<MoveListener> listeners) {
        initializeEmptyCells(map);
        spawnGrass(map, plants,6,5);
        spawnTree(map, plants, 5, 9);
        spawnHerbivore(map, creatures,7, 3,  listeners);
        spawnHerbivore(map, creatures,5, 4,  listeners);
        spawnPredator(map, creatures,0, 1,  listeners);
        spawnPredator(map, creatures,0, 8,  listeners);
    }

    private void initializeEmptyCells(Map<Coordinates, Entity> map) {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 20; column++) {
                Coordinates coordinate = new Coordinates(row, column);  //создали координату
                Entity emptyPlace = new EmptyPlace(coordinate, "⬛");      //создали пустую клетку
                map.put(coordinate, emptyPlace);  //добавили пустую клетку по координате(ключу) в мапу
            }
        }
    }

    private void spawnGrass(Map<Coordinates, Entity> map, List<Plant> plants, int row, int column) {
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Grass grass = new Grass(spawnCoordinates, GRASSFIGURE);
        map.put(spawnCoordinates, grass);
        plants.add(grass);
    }

    private void spawnTree(Map<Coordinates, Entity> map, List<Plant> plants, int row, int column) {
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Tree wood = new Tree(spawnCoordinates, TREEFIGURE);
        map.put(spawnCoordinates, wood);
        plants.add(wood);
    }


    private void spawnHerbivore(Map<Coordinates, Entity> map, List<Creature> creatures, int row, int column, List<MoveListener> listeners) {
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, HERBIVOREFIGURE,  listeners);
        map.put(spawnCoordinates, herbivore);
        creatures.add(herbivore);
    }

    private void spawnPredator(Map<Coordinates, Entity> map, List<Creature> creatures, int row, int column, List<MoveListener> listeners) {
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Predator predator = new Predator(spawnCoordinates, PREDATIRFIGURE, listeners);
        map.put(spawnCoordinates, predator);
        creatures.add(predator);
    }
}
