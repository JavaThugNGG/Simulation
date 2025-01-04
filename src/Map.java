import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map {
    private static List<Coordinates> coordinates = new ArrayList<>();
    private static java.util.Map<java.util.Map<Integer, Integer>, Entity> map = new HashMap<>(25);

    public static void initializeDefualtMap() {
        for (int horizontal = 1; horizontal < 6; horizontal++) {    //синициализировали набор координат для карты
            for (int vertical = 1; vertical < 6; vertical++) {
                Coordinates coordinate = new Coordinates(horizontal, vertical);
                coordinates.add(coordinate);
            }
        }



    }
}
