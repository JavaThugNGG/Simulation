import java.util.*;


public class MyMap {
    private static List<Coordinates> coordinates = new LinkedList<>();
    private static Map<Coordinates, Entity> map = new TreeMap<>();

    public static void initializeDefualtMap(List<Coordinates> listCoordinates, List<Entity> listEmptyPlaces) {
        for (int i = 0; i < listCoordinates.size(); i++) {
            Coordinates coordinate = listCoordinates.get(i);       // Получаем координаты
            Entity emptyPlace = listEmptyPlaces.get(i);             // Получаем объект (например, EmptyPlace)

            map.put(coordinate, emptyPlace);  // Добавляем пару (координаты, объект) в map
        }
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            System.out.print(entry.getValue());

            if (entry.getKey().getVertical() == 20) {
                System.out.print("\n");
            }

        }
    }

}
