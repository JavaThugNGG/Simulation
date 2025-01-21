package Simulation.Actions;

import Simulation.Coordinates;
import Simulation.MoveListener;
import Simulation.Entities.Entity;

import java.util.List;
import java.util.Map;

public abstract class CreatureSpawnAction{

    public abstract void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners);
}
