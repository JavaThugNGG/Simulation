import java.util.Map;

public class WorldPrinter {
    private final int ROWS;
    private final int COLUMNS;

    public WorldPrinter(int rows, int columns) {
        this.ROWS = rows;
        this.COLUMNS = columns;
    }

    public void print(Map<Coordinates, Entity> inputMap) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
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
