package Simulation;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();

        Thread initializationThread = new Thread(simulation::initializeSimulation);     // Поток для инициализации симуляции
        Thread runThread = new Thread(simulation::startSimulation);

        initializationThread.start();                                                   // Запустили этот поток

        try {
            initializationThread.join();            // Подождали пока инициализация завершится
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Ошибка при инициализации!");
        }

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
                case 1 -> runThread.start();
                case 2 -> simulation.pauseSimulation();
                case 3 -> simulation.resumeSimulation();
                case 4 -> {
                    simulation.endSimulation();
                    try {
                            runThread.join();  // Ждем завершения потока симуляции, если он еще работает
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Ошибка при завершении потока, который отвечает за цикл симуляции.");
                    }
                    exit = true;
                }
                default -> System.out.println("Неверный выбор. Повторите ввод.");
            }
        }
        scanner.close();
    }
}

