import java.util.List;
import java.util.Map;

public class MapInitializer {

    public void initializeMap(Map<Coordinates, Entity> map, List<Grass> grasses, List<Herbivore> herbivores) {
        initializeEmptyCells(map);
        spawnGrass(map, grasses,4,10);
        spawnGrass(map, grasses,4,19);
        spawnHerbivore(map, herbivores,7, 3);
        spawnHerbivore(map, herbivores,5, 4);
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

    private void spawnHerbivore(Map<Coordinates, Entity> map, List<Herbivore> herbivores, int row, int column) {
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, "\uD83D\uDC30");
        map.put(spawnCoordinates, herbivore);
        herbivores.add(herbivore);
    }
}
