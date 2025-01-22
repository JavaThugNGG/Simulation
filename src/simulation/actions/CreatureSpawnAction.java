package simulation.actions;

import simulation.Coordinates;
import simulation.MoveListener;
import simulation.entities.Entity;

import java.util.List;
import java.util.Map;

public abstract class CreatureSpawnAction{

    public abstract void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners);
}
