package simulation.entities;

import simulation.Coordinates;
import simulation.MoveListener;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure, listeners);
    }
}
