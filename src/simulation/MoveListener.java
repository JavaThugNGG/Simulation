package simulation;

import simulation.entities.Creature;

public interface MoveListener {

    void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates);
}

