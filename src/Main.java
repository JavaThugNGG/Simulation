public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру Симуляция!\n");     //Консольная работа будет тут
        Simulation simulation = new Simulation();
        simulation.runSimulation();
        System.out.println("Симуляция окончена!");
    }
}