public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру Симуляция!\n");
        Simulation simulation = new Simulation();
        simulation.startSimulation();
        System.out.println("Вы победили!");
    }
}