package Simulation;

import Simulation.Entities.Creature;

public interface MoveListener {     //имплементится классом MapController

    void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates);
}

