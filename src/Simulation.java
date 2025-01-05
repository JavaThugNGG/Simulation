import java.util.*;


public class Simulation {
    private static List<Coordinates> coordinatesList = new ArrayList<>();     //список всех координат
    private static List<Entity> emptyPlacesList = new ArrayList<>();          //список пустых объектов, которые стоят на этих координатах

    private static Map<Coordinates, Entity> map = new TreeMap<>();            //мапа для карты
    private static int herbivoreAlive = 1;
    private static Entity predator;
    private static Entity herbivore;


    public static void startSimulation() {
        initializeEmptyMap();
        checkHerbivore();
        do {
            Simulation.printMap();
            checkHerbivore();
            Simulation.moveLeft(predator);
        } while (herbivoreAlive != 0);
        Simulation.printMap();             //конечное состояние карты вывели
    }


    public static void initializeEmptyMap() {
        initializeEmptyCells();
        spawnPredator();
        spawnHerbivore();
    }


    public static void initializeEmptyCells() {
        for (int horizontal = 0; horizontal < 10; horizontal++) {
            for (int vertical = 0; vertical < 20; vertical++) {
                Coordinates coordinate = new Coordinates(horizontal, vertical);  //создали координату
                Entity emptyPlace = new EmptyPlace(coordinate, "⬛");      //создали пустую клетку
                map.put(coordinate, emptyPlace);  //добавили пустую клетку по координате(ключу) в мапу
            }
        }
    }


    public static void spawnPredator() {
        Coordinates spawnCoordinates = new Coordinates(0,19);
        predator = new Predator(spawnCoordinates, "\uD83D\uDC2F");
        map.put(spawnCoordinates, predator);
    }


    public static void spawnHerbivore() {
        Coordinates spawnCoordinates = new Coordinates(0,10);
        herbivore = new Herbivore(spawnCoordinates, "\uD83D\uDC30");
        map.put(spawnCoordinates, herbivore);
    }


    public static void moveLeft(Entity entity) {
        Coordinates coordinates = entity.getCoordinates();
        int newLine = coordinates.getLine();
        int newColumn = coordinates.getColumn() - 1;
        Coordinates newCoordinates = new Coordinates(newLine, newColumn);
        Entity emptyPlace = new EmptyPlace(coordinates, "⬛");

        map.put(newCoordinates, entity);     //сдвинули фигуру
        map.put(coordinates, emptyPlace);    //затерли фигуру на старой позиции
        predator.replaceCoordinates(newCoordinates);     //обновили координаты в самой сущности
        checkHerbivore();
    }


    public static void checkHerbivore() {
        Coordinates predatorCoordinates = predator.getCoordinates();
        int line = predatorCoordinates.getLine();
        int column = predatorCoordinates.getColumn();
        int leftPosition = column - 1;
        Coordinates coordinateToCheck = new Coordinates(line, leftPosition);

        Entity entityOnCoordinate = map.get(coordinateToCheck);
        if (entityOnCoordinate instanceof Herbivore) {
            Entity emptyPlace = new EmptyPlace(coordinateToCheck, "⬛");
            map.put(coordinateToCheck, emptyPlace);      //травоядное пропадает
            herbivoreAlive = herbivoreAlive - 1;
        }
    }

    public static void printMap() {
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            System.out.print(entry.getValue());

            if (entry.getKey().getColumn() == 19) {
                System.out.print("\n");
            }
        }
        System.out.println();
    }
}
