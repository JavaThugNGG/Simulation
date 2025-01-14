package Simulation.Entities;

import Simulation.Coordinates;
import Simulation.MoveListener;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure, listeners);
    }
}
