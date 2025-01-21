package Simulation;

import Simulation.Entities.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class PathFinder {
    final int WORLD_ROWS = Simulation.getWORLD_ROWS();
    final int WORLD_COLUMNS = Simulation.getWORLD_COLUMNS();

    public List<Coordinates> findPathToVictim(Map<Coordinates, Entity> map, Creature creature) {

        Coordinates start = creature.getCoordinates();
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>(); // карта, где для каждой точки хранится предыдущая пройденная
        Set<Coordinates> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        if (creature instanceof Predator) {
            while (!queue.isEmpty()) {
                Coordinates current = queue.poll();

                if (map.get(current) instanceof Herbivore) {     //если нашли травоядного -> восстанавливаем путь
                    return recoverPath(cameFrom, start, current);
                }
                for (Coordinates neighbor : getNeighborCells(current)) {
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
                    return recoverPath(cameFrom, start, current);
                }
                for (Coordinates neighbor : getNeighborCells(current)) {
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


    private List<Coordinates> getNeighborCells(Coordinates coordinates) {
        List<Coordinates> neighbors = new ArrayList<>();
        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        int maxRowIndex = WORLD_ROWS - 1;
        int maxColumnIndex = WORLD_COLUMNS - 1;

        int upRow = row - 1;
        int downRow = row + 1;
        int leftColumn = column - 1;
        int rightColumn = column + 1;

        if (row > 0) {
            neighbors.add(new Coordinates(upRow, column));
        }
        if (row < maxRowIndex) {
            neighbors.add(new Coordinates(downRow, column));
        }
        if (column > 0) {
            neighbors.add(new Coordinates(row, leftColumn));
        }
        if (column < maxColumnIndex) {
            neighbors.add(new Coordinates(row, rightColumn));
        }
        return neighbors;
    }

    private List<Coordinates> recoverPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates goal) {  //восстановить путь
        List<Coordinates> path = new ArrayList<>();      //путь от goal до start
        Coordinates current = goal;

        while (!current.equals(start)) {                //пока не пришли в точку goal
            path.add(current);
            current = cameFrom.get(current);            //берем точку из которой пришли
        }

        Collections.reverse(path);
        return path;
    }
}
