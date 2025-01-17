package Simulation.Entities;

import Simulation.Coordinates;
import Simulation.MoveListener;

import java.util.List;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure, listeners);
    }
}
