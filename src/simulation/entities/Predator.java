package simulation.entities;

import simulation.Coordinates;
import simulation.MoveListener;

import java.util.List;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure, listeners);
    }
}
