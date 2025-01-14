import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class CreatureSpawnAction{

    public abstract void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, List<MoveListener> listeners);
}
