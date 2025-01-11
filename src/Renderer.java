import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Renderer {

    public void printMap(Map<Coordinates, Entity> inputMap) {
        Map<Coordinates, Entity> sortedMap = new TreeMap<>(inputMap);

        for (Map.Entry<Coordinates, Entity> entry : sortedMap.entrySet()) {
            System.out.print(entry.getValue());
            if (entry.getKey().getColumn() == 19) {
                System.out.print("\n");
            }
        }
        System.out.println();
    }
}
