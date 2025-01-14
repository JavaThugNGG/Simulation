package Simulation.Actions;

import Simulation.Entities.Entity;
import Simulation.Simulation;
import Simulation.Coordinates;

import java.util.List;
import java.util.Map;

public abstract class PlantSpawnAction{
    protected final int WORLD_ROWS = Simulation.getWORLD_ROWS();
    protected final int WORLD_COLUMNS = Simulation.getWORLD_COLUMNS();

    public abstract void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities);
}
