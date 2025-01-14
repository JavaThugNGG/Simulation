import java.util.*;

public class PathFinder {

    public List<Coordinates> findPathToVictim(Map<Coordinates, Entity> map, Creature creature) {

        Coordinates start = creature.getCoordinates();
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>(); // для восстановления пути
        Set<Coordinates> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        if (creature instanceof Predator) {
            while (!queue.isEmpty()) {
                Coordinates current = queue.poll();

                if (map.get(current) instanceof Herbivore) {     //если нашли травоядного -> восстанавливаем путь
                    return reconstructPath(cameFrom, start, current);
                }
                for (Coordinates neighbor : getNeighbors(current)) {
                    if (!visited.contains(neighbor)  && !(map.get(neighbor) instanceof Plant) && !(map.get(neighbor) instanceof Predator)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        cameFrom.put(neighbor, current);
                    }
                }
            }
        }

        if (creature instanceof Herbivore) {
            while (!queue.isEmpty()) {
                Coordinates current = queue.poll();

                if (map.get(current) instanceof Grass) {       //если нашли траву -> восстанавливаем путь
                    return reconstructPath(cameFrom, start, current);
                }
                for (Coordinates neighbor : getNeighbors(current)) {
                    if (!visited.contains(neighbor)  && !(map.get(neighbor) instanceof Herbivore) && !(map.get(neighbor) instanceof Tree) && !(map.get(neighbor) instanceof Predator)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                        cameFrom.put(neighbor, current);
                    }
                }
            }
        }

        return Collections.emptyList(); // если путь не найден
    }


    private List<Coordinates> getNeighbors(Coordinates coordinates) {
        List<Coordinates> neighbors = new ArrayList<>();
        int line = coordinates.getRow();
        int column = coordinates.getColumn();

        if (line > 0) neighbors.add(new Coordinates(line - 1, column));        // вверх
        if (line < 9) neighbors.add(new Coordinates(line + 1, column));        // вниз
        if (column > 0) neighbors.add(new Coordinates(line, column - 1));   // влево
        if (column < 19) neighbors.add(new Coordinates(line, column + 1));  // вправо

        return neighbors;
    }

    private List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates goal) {  //восстановить путь
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
