import java.util.*;

public class PathFinder {


    public List<Coordinates> findPathToGrass(Map<Coordinates, Entity> map, Coordinates start) { //реализует алгоритм поиска в ширину
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>(); // Для восстановления пути
        Set<Coordinates> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {   //пока есть клетки для обработки в очереди
            Coordinates current = queue.poll();    //взяли клетку из очередь

            // Если нашли траву, восстанавливаем путь
            if (map.get(current) instanceof Grass) {
                return reconstructPath(cameFrom, start, current);
            }
            // Проверяем соседей
            for (Coordinates neighbor : getNeighbors(current)) {   //взяли клетку из очереди
                if (!visited.contains(neighbor) && map.containsKey(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);   //содержит из какой клетки мы пришли в текущую
                }
            }
        }
        return Collections.emptyList(); // Если путь не найден
    }


    // Метод для получения соседей
    private List<Coordinates> getNeighbors(Coordinates coord) {
        List<Coordinates> neighbors = new ArrayList<>();
        int line = coord.getRow();
        int column = coord.getColumn();

        if (line > 0) neighbors.add(new Coordinates(line - 1, column)); // Вверх
        if (line < 9) neighbors.add(new Coordinates(line + 1, column)); // Вниз
        if (column > 0) neighbors.add(new Coordinates(line, column - 1)); // Влево
        if (column < 19) neighbors.add(new Coordinates(line, column + 1)); // Вправо

        return neighbors;
    }


    // Метод для восстановления пути
    private List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates goal) {
        List<Coordinates> path = new ArrayList<>();   //здесь будет лежать путь
        Coordinates current = goal;           //текущая клетка - клетка где трава

        while (!current.equals(start)) {    //пока точка не та, из которой искали путь
            path.add(current);                 //добавляем координаты чтобы идти (обратные координаты)
            current = cameFrom.get(current);    //берется предыдущая координата из мапы (откуда пришли), по факту это следующая
        }

        Collections.reverse(path);
        return path;
    }


}
