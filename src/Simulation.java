import java.util.*;


public class Simulation {
    private  List<Coordinates> coordinatesList = new ArrayList<>();     //список всех координат
    private  List<Entity> emptyPlacesList = new ArrayList<>();          //список пустых объектов, которые стоят на этих координатах

    private  Map<Coordinates, Entity> map = new TreeMap<>();            //мапа для карты
    private  int grassAlive = 1;
    private  Entity herbivore;
    private List<Grass> grasses = new ArrayList<>();

    public Simulation() {
    }


    public void startSimulation() {
        initializeEmptyMap();
        do {;
            printMap();
            eatGrassIfAble();
        } while (grassAlive != 0);
        printMap();             //конечное состояние карты вывели
    }

    public void eatGrassIfAble() {
        List<Coordinates> path = followPath();
        path = followPath();
        if (!path.isEmpty()) {
            move(path);
        }
    }

    public List<Coordinates> followPath() {
        PathFinder pathFinder = new PathFinder();
        return pathFinder.findPathToGrass(map, herbivore.getCoordinates());
    }


    public void move(List<Coordinates> path) {
            Coordinates newCoordinates = path.getFirst();
            Coordinates oldCoordinates = herbivore.coordinates;
            herbivore.coordinates = newCoordinates;
            map.put(newCoordinates, herbivore);
            Entity emptyPlace = new EmptyPlace(oldCoordinates, "⬛");
            map.put(oldCoordinates, emptyPlace);
    }


    public void initializeEmptyMap() {
        initializeEmptyCells();
        spawnGrass(8,2);
        spawnGrass(4,7);
        spawnHerbivore();
    }


    public  void initializeEmptyCells() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 20; column++) {
                Coordinates coordinate = new Coordinates(row, column);  //создали координату
                Entity emptyPlace = new EmptyPlace(coordinate, "⬛");      //создали пустую клетку
                map.put(coordinate, emptyPlace);  //добавили пустую клетку по координате(ключу) в мапу
            }
        }
    }


    public void spawnGrass(int row, int column) {
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Grass grass = new Grass(spawnCoordinates, "\uD83C\uDF3F");
        map.put(spawnCoordinates, grass);
        grasses.add(grass);
    }


    public void spawnHerbivore() {
        Coordinates spawnCoordinates = new Coordinates(7,3);
        herbivore = new Herbivore(spawnCoordinates, "\uD83D\uDC30");
        map.put(spawnCoordinates, herbivore);
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
