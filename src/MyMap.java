import java.util.*;


public class MyMap {
    private static List<Coordinates> listCoordinates = new ArrayList<>();
    private static List<Entity> listEmptyPlaces = new ArrayList<>();

    private static Map<Coordinates, Entity> map = new TreeMap<>();
    private static int herbivoreAlive = 1;
    private static Entity predator;
    private static Entity herbivore;



    public static void initializeDefualtMap(List<Coordinates> listCoordinates, List<Entity> listEmptyPlaces) {
        for (int horizontal = 0; horizontal < 10; horizontal++) {    //теперь в списке хранятся координаты с пустыми фигурами
            for (int vertical = 0; vertical < 20; vertical++) {
                Coordinates coordinate = new Coordinates(horizontal, vertical);
                Entity emptyPlace = new EmptyPlace(coordinate, "⬛");
                listCoordinates.add(coordinate);
                listEmptyPlaces.add(emptyPlace);
            }
        }

        for (int i = 0; i < listCoordinates.size(); i++) {
            Coordinates coordinate = listCoordinates.get(i);       // Получаем координаты
            Entity emptyPlace = listEmptyPlaces.get(i);             // Получаем объект (например, EmptyPlace)

            map.put(coordinate, emptyPlace);  // Добавляем пару (координаты, объект) в map
        }


        Coordinates predatorSpawnCoordonates = new Coordinates(0,19);
        predator = new Predator(predatorSpawnCoordonates, "\uD83D\uDC2F");
        MyMap.spawnEntity(predator);


        Coordinates herbivoreSpawnCoordonates = new Coordinates(0,10);
        herbivore = new Herbivore(herbivoreSpawnCoordonates, "\uD83D\uDC30");
        MyMap.spawnEntity(herbivore);




        printMap();
    }

    public static void printMap() {
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            System.out.print(entry.getValue());

            if (entry.getKey().getVertical() == 19) {
                System.out.print("\n");
            }
        }
    }

    public static void spawnEntity(Entity entity) {
        Coordinates coordinates = entity.getCoordinates();
        map.put(coordinates, entity);
    }

    public static void moveLeft(Entity entity) {
        Coordinates coordinates = entity.getCoordinates();

        int newHorizontal = coordinates.getHorizontal();
        int newVertical = coordinates.getVertical() - 1;
        Coordinates newCoordinates = new Coordinates(newHorizontal, newVertical);

        Entity emptyPlace = new EmptyPlace(coordinates, "⬛");

        map.put(newCoordinates, entity);
        map.put(coordinates, emptyPlace);
        predator.replaceCoordinates(newCoordinates);
    }

    public static void checkHerbivoreAndKill() {

        Coordinates predatorCoordinates = predator.getCoordinates();
        int horizontal = predatorCoordinates.getHorizontal();
        int vertical = predatorCoordinates.getVertical();
        int leftPosition = vertical - 1;
        Coordinates coordinateToCheck = new Coordinates(horizontal, leftPosition);

        Coordinates oldCoordinates = new Coordinates(horizontal, vertical);

        Entity entityOnCoordinate = map.get(coordinateToCheck);
        if (entityOnCoordinate instanceof Herbivore) {
            Entity emptyPlace = new EmptyPlace(coordinateToCheck, "⬛");
            map.put(coordinateToCheck, emptyPlace);
            herbivoreAlive = herbivoreAlive - 1;

        }
    }


    public static void gameLoop() {
        initializeDefualtMap(listCoordinates, listEmptyPlaces);
        System.out.println();

        do {
            MyMap.printMap();
            System.out.println();
            checkHerbivoreAndKill();
            MyMap.moveLeft(predator);


        } while (herbivoreAlive != 0);

    }


}
