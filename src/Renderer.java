import java.util.Map;
import java.util.TreeMap;

public class Renderer {

    public void printMap(Map<Coordinates, Entity> inputMap) {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 20; column++) {
                Coordinates coordinate = new Coordinates(row, column); // Создаём текущую координату

                // Проверяем наличие координаты в карте
                if (inputMap.containsKey(coordinate)) {
                    Entity entity = inputMap.get(coordinate);
                    System.out.print(entity); // Вывод значения
                } else {
                    System.out.print("⬛"); // Заглушка для отсутствующих ключей
                }
            }
            System.out.print("\n"); // Переход на следующую строку
        }
        System.out.println();
    }
}
