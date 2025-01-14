package Simulation;

import Simulation.Entities.Creature;

public interface MoveListener {

    void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates);
}

