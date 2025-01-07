import java.util.List;
import java.util.Map;

public class MapInitializer {

    public void initializeMap(Map<Coordinates, Entity> map, List<Grass> grasses, List<Creature> creatures, List<MoveListener> listeners) {
        initializeEmptyCells(map);
        spawnGrass(map, grasses,4,10);
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

    private void spawnGrass(Map<Coordinates, Entity> map, List<Grass> grasses, int row, int column) {
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Grass grass = new Grass(spawnCoordinates, "\uD83C\uDF3F");
        map.put(spawnCoordinates, grass);
        grasses.add(grass);
    }

    private void spawnHerbivore(Map<Coordinates, Entity> map, List<Creature> creatures, int row, int column, List<MoveListener> listeners) {
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, "\uD83D\uDC30",  listeners);
        map.put(spawnCoordinates, herbivore);
        creatures.add(herbivore);
    }

    private void spawnPredator(Map<Coordinates, Entity> map, List<Creature> creatures, int row, int column, List<MoveListener> listeners) {
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Predator predator = new Predator(spawnCoordinates, "\uD83D\uDC3A", listeners);
        map.put(spawnCoordinates, predator);
        creatures.add(predator);
    }
}
