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
    private final Map<Coordinates, Entity> map;
    private Coordinates start;


    public PathFinder(Map<Coordinates, Entity> map) {
        this.map = map;
    }

    public List<Coordinates> findPathToVictim(Creature creature) {
        start = creature.getCoordinates();

        if (creature instanceof Predator) {
            return findPathToGoal(Herbivore.class);
        }
        if (creature instanceof Herbivore) {
            return findPathToGoal(Grass.class);
        }

        return Collections.emptyList();
    }

    private List<Coordinates> findPathToGoal(Class<?> goalClass) {
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            if (isGoalAtCoordinates(current, goalClass)) {
                return recoverPath(cameFrom, start, current);
            }

            for (Coordinates neighbor : getNeighborCells(current)) {
                if (!visited.contains(neighbor) && isValidMove(neighbor, goalClass)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return Collections.emptyList();
    }

    private boolean isValidMove(Coordinates neighbor, Class<?> goalClass) {
        Entity entity = map.get(neighbor);

        // Здесь проверка на тип сущности
        if (goalClass == Herbivore.class) {
            return !(entity instanceof Predator || entity instanceof Tree || entity instanceof Grass);
        } else if (goalClass == Grass.class) {
            return !(entity instanceof Predator || entity instanceof Tree || entity instanceof Herbivore);
        }

        return true;
    }

    private boolean isGoalAtCoordinates(Coordinates current, Class<?> goalClass) {
        Entity currentEntity = map.get(current);  // Получаем сущность на текущей координате
        return goalClass.isInstance(currentEntity);  // Проверяем, является ли сущность целью
    }

    private List<Coordinates> getNeighborCells(Coordinates coordinates) {
        List<Coordinates> neighbors = new ArrayList<>();
        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        int maxRowIndex = Simulation.WORLD_ROWS - 1;
        int maxColumnIndex = Simulation.WORLD_COLUMNS - 1;

        if (row > 0) {
            neighbors.add(new Coordinates(row - 1, column));
        }
        if (row < maxRowIndex) {
            neighbors.add(new Coordinates(row + 1, column));
        }
        if (column > 0) {
            neighbors.add(new Coordinates(row, column - 1));
        }
        if (column < maxColumnIndex) {
            neighbors.add(new Coordinates(row, column + 1));
        }

        return neighbors;
    }

    private List<Coordinates> recoverPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates goal) {
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

