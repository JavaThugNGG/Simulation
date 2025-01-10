import java.util.*;

public class PathFinder {

    public List<Coordinates> findPathToVictim(Map<Coordinates, Entity> map, Creature creature) {
        Coordinates start = creature.getCoordinates();
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>(); // Для восстановления пути
        Set<Coordinates> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        if (creature instanceof Predator) {
            while (!queue.isEmpty()) {
                Coordinates current = queue.poll();

                if (map.get(current) instanceof Herbivore) {
                    return reconstructPath(cameFrom, start, current);
                }
                for (Coordinates neighbor : getNeighbors(current)) {   //взяли клетку из очереди (проверяем соседей тут)
                    if (!visited.contains(neighbor) && map.containsKey(neighbor) && !(map.get(neighbor) instanceof Plant) &&!(map.get(neighbor) instanceof Predator)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        cameFrom.put(neighbor, current);   //содержит из какой клетки мы пришли в текущую
                    }
                }
            }
        }

        if (creature instanceof Herbivore) {
            while (!queue.isEmpty()) {   //пока есть клетки для обработки в очереди
                Coordinates current = queue.poll();    //взяли клетку из очередь

                if (map.get(current) instanceof Grass) {       // Если нашли траву, восстанавливаем путь
                    return reconstructPath(cameFrom, start, current);
                }
                for (Coordinates neighbor : getNeighbors(current)) {   //взяли клетку из очереди (проверяем соседей тут)
                    if (!visited.contains(neighbor) && map.containsKey(neighbor) && !(map.get(neighbor) instanceof Herbivore) && !(map.get(neighbor) instanceof Tree)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                        cameFrom.put(neighbor, current);   //содержит из какой клетки мы пришли в текущую
                    }
                }
            }
        }

        return Collections.emptyList(); // Если путь не найден
    }


    private List<Coordinates> getNeighbors(Coordinates coordinates) {
        List<Coordinates> neighbors = new ArrayList<>();
        int line = coordinates.getRow();
        int column = coordinates.getColumn();

        if (line > 0) neighbors.add(new Coordinates(line - 1, column)); // Вверх
        if (line < 9) neighbors.add(new Coordinates(line + 1, column)); // Вниз
        if (column > 0) neighbors.add(new Coordinates(line, column - 1)); // Влево
        if (column < 19) neighbors.add(new Coordinates(line, column + 1)); // Вправо

        return neighbors;
    }

    private List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates goal) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = goal;

        while (!current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
