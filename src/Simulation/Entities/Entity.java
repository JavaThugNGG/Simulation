package Simulation.Entities;

import Simulation.Coordinates;

public abstract class Entity {
    protected Coordinates coordinates;
    protected final String figure;

    public Entity(Coordinates coordinates, String figure) {
        this.coordinates = coordinates;
        this.figure = figure;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String toString() {
        return figure;
    }
}
