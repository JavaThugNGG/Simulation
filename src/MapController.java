import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapController implements MoveListener {
    private Map<Coordinates, Entity> map;
    private int width;
    private int height;


    private final String GRASSFIGURE = "\uD83C\uDF3F";
    private final String TREEFIGURE = "\uD83C\uDF33";
    private final String HERBIVOREFIGURE = "\uD83D\uDC30";
    private final String PREDATIRFIGURE = "\uD83D\uDC3A";

    public MapController(Map<Coordinates, Entity> map, int width, int height, List<MoveListener> listeners) {
        this.map = map;
        this.width = width;
        this.height = height;

        List<Entity> generatedEntities = generateEntities(listeners);

        for (Entity entity : generatedEntities) {
            Coordinates entityCoordinates = entity.getCoordinates();
            map.put(entityCoordinates, entity);                       //и мапа заполнена существами
        }
    }



    @Override
    public void onMove(Creature creature, Coordinates oldCoordinates, Coordinates newCoordinates) {
        map.put(newCoordinates, creature);
        map.remove(oldCoordinates);
    }

    /*public void initializeMap(List<Plant> plants, List<Creature> creatures, List<MoveListener> listeners) {
        spawnGrass(map, plants,6,5);
        spawnTree(map, plants, 5, 9);
        spawnHerbivore(map, creatures,7, 3,  listeners);
        spawnHerbivore(map, creatures,5, 4,  listeners);
        spawnPredator(map, creatures,0, 1,  listeners);
        spawnPredator(map, creatures,0, 8,  listeners);
    }*/


    private void spawnGrass(Map<Coordinates, Entity> map, List<Plant> plants, int row, int column) {
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Grass grass = new Grass(spawnCoordinates, GRASSFIGURE);
        map.put(spawnCoordinates, grass);
        plants.add(grass);
    }

    private void spawnTree(Map<Coordinates, Entity> map, List<Plant> plants, int row, int column) {
        Coordinates spawnCoordinates = new Coordinates(row,column);
        Tree wood = new Tree(spawnCoordinates, TREEFIGURE);
        map.put(spawnCoordinates, wood);
        plants.add(wood);
    }


    private void spawnHerbivore(Map<Coordinates, Entity> map, List<Creature> creatures, int row, int column, List<MoveListener> listeners) {
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Herbivore herbivore = new Herbivore(spawnCoordinates, HERBIVOREFIGURE,  listeners);
        map.put(spawnCoordinates, herbivore);
        creatures.add(herbivore);
    }

    private void spawnPredator(Map<Coordinates, Entity> map, List<Creature> creatures, int row, int column, List<MoveListener> listeners) {
        Coordinates spawnCoordinates = new Coordinates(row, column);
        Predator predator = new Predator(spawnCoordinates, PREDATIRFIGURE, listeners);
        map.put(spawnCoordinates, predator);
        creatures.add(predator);
    }

    private List<Entity> generateEntities(List<MoveListener> listeners) {
        List<Entity> generatedEntities = new ArrayList<>();
        Coordinates grassCoordinates = new Coordinates(6,5);
        Grass grass1 = new Grass(grassCoordinates,GRASSFIGURE);
        generatedEntities.add(grass1);

        Coordinates treeCoordinates = new Coordinates(5,9);
        Tree tree1 = new Tree(treeCoordinates,TREEFIGURE);
        generatedEntities.add(tree1);

        Coordinates herbivoreCoordinates1 = new Coordinates(7,3);
        Herbivore herbivore1 = new Herbivore(herbivoreCoordinates1,HERBIVOREFIGURE, listeners);
        generatedEntities.add(herbivore1);

        Coordinates herbivoreCoordinates2 = new Coordinates(5,4);
        Herbivore herbivore2 = new Herbivore(herbivoreCoordinates2,HERBIVOREFIGURE, listeners);
        generatedEntities.add(herbivore2);

        Coordinates predatorCoordinates1 = new Coordinates(0,1);
        Predator predator1 = new Predator(predatorCoordinates1,PREDATIRFIGURE, listeners);
        generatedEntities.add(predator1);

        Coordinates predatorCoordinates2 = new Coordinates(0,8);
        Predator predator2 = new Predator(predatorCoordinates2,PREDATIRFIGURE, listeners);
        generatedEntities.add(predator2);


        return generatedEntities;
    }
}
