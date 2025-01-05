import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру Симуляция!");


        List<Coordinates> listCoordinates = new ArrayList<>();
        List<Entity> listEmptyPlaces = new ArrayList<>();

        for (int horizontal = 1; horizontal < 11; horizontal++) {    //теперь в списке хранятся координаты с пустыми фигурами
            for (int vertical = 1; vertical < 21; vertical++) {
                Coordinates coordinate = new Coordinates(horizontal, vertical);
                Entity emptyPlace = new EmptyPlace(coordinate, "⬛");
                listCoordinates.add(coordinate);
                listEmptyPlaces.add(emptyPlace);
            }
        }

        MyMap.initializeDefualtMap(listCoordinates, listEmptyPlaces);



    }
}