package simulation.actions;

import simulation.entities.Entity;
import simulation.Coordinates;

import java.util.List;
import java.util.Map;

public abstract class PlantSpawnAction{

    public abstract void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities);
}
