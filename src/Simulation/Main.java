package Simulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.initializeSimulation();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Запуск симуляции");
            System.out.println("2 - Пауза");
            System.out.println("3 - Продолжить");
            System.out.println("4 - Выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> simulation.startSimulation();
                case 2 -> simulation.pauseSimulation();
                case 3 -> simulation.resumeSimulation();
                case 4 -> {
                    simulation.pauseSimulation(); // Остановка перед выходом
                    exit = true;
                }
                default -> System.out.println("Неверный выбор. Повторите ввод.");
            }
        }

        scanner.close();
    }
}
