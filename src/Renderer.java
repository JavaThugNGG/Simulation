import java.util.Map;

public class Renderer {
    public void printMap(Map<Coordinates, Entity> map) {
        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
            System.out.print(entry.getValue());

            if (entry.getKey().getColumn() == 19) {
                System.out.print("\n");
            }
        }
        System.out.println();
    }
}
