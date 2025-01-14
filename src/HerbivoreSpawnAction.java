import java.util.List;
import java.util.Map;
import java.util.Random;

public class HerbivoreSpawnAction extends CreatureSpawnAction {

    public void perform(Map<Coordinates, Entity> map, List<Entity> generatedEntities, int worldRows, int worldColumns, List<MoveListener> listeners) {
        Random random = new Random();
        int row = random.nextInt(worldRows);
        int column = random.nextInt(worldColumns);
        String herbivoreFigure = "\uD83D\uDC30";

        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, herbivoreFigure, listeners);    //создали объект с рандомными координатами
        map.put(spawnCoordinates, herbivore);                                                 //положили его в мапу
        generatedEntities.add(herbivore);                                                     //положили его в список существ
    }
}
