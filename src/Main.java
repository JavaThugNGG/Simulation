import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру Симуляция!");


        List<Coordinates> listCoordinates = new ArrayList<>();
        List<Entity> listEmptyPlaces = new ArrayList<>();

        for (int horizontal = 0; horizontal < 10; horizontal++) {    //теперь в списке хранятся координаты с пустыми фигурами
            for (int vertical = 0; vertical < 20; vertical++) {
                Coordinates coordinate = new Coordinates(horizontal, vertical);
                Entity emptyPlace = new EmptyPlace(coordinate, "⬛");
                listCoordinates.add(coordinate);
                listEmptyPlaces.add(emptyPlace);
            }
        }

        MyMap.initializeDefualtMap(listCoordinates, listEmptyPlaces);



        Coordinates predatorSpawnCoordonates = new Coordinates(0,19);
        Entity predator = new Predator(predatorSpawnCoordonates, "\uD83D\uDC2F");

        MyMap.spawnEntity(predator);


        System.out.println();
        MyMap.printMap();





    }
}