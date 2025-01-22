package Simulation.Entities;

import Simulation.MoveListener;
import Simulation.Coordinates;

import java.util.List;

public abstract class Creature extends Entity {
    private final List<MoveListener> listeners;

    public Creature(Coordinates coordinates, String figure, List<MoveListener> listeners) {
        super(coordinates, figure);
        this.listeners = listeners;
    }

    public void makeMove(Coordinates newCoordinates) {
        Coordinates oldCoordinates = this.coordinates;
        this.coordinates = newCoordinates;
        notifyMoveListeners(oldCoordinates, newCoordinates);
    }

    private void notifyMoveListeners(Coordinates oldCoordinates, Coordinates newCoordinates) {
        for (MoveListener listener : listeners) {
            listener.onMove(this, oldCoordinates, newCoordinates);
        }
    }
}
