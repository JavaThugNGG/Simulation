import java.util.Map;

public class WorldPrinter {
    private final int WORLD_ROWS = Simulation.getWORLD_ROWS();
    private final int WORLD_COLUMNS = Simulation.getWORLD_COLUMNS();

    public void print(Map<Coordinates, Entity> inputMap) {
        for (int i = 0; i < WORLD_ROWS; i++) {
            for (int j = 0; j < WORLD_COLUMNS; j++) {
                Coordinates coordinate = new Coordinates(i, j);   // потому что мапа кушает класс Coordinates

                if (inputMap.containsKey(coordinate)) {           // проверяем наличие координаты в карте
                    Entity entity = inputMap.get(coordinate);
                    System.out.print(entity);                     // вывод фигурки существа
                } else {
                    System.out.print("⬛");                       // заглушка если существа не было в мапе
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}