import java.util.List;
import java.util.Map;

public abstract class CreatureSpawnAction{
    protected final int WORLD_ROWS = Simulation.getWORLD_ROWS();
    protected final int WORLD_COLUMNS = Simulation.getWORLD_COLUMNS();

    public abstract void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners);
}
