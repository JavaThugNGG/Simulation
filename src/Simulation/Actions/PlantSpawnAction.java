package Simulation.Actions;

import Simulation.Entities.Entity;
import Simulation.Simulation;
import Simulation.Coordinates;

import java.util.List;
import java.util.Map;

public abstract class PlantSpawnAction{

    public abstract void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities);
}
