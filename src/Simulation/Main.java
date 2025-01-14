package Simulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        System.out.println("Добро пожаловать в симуляцию!\n");

        Simulation simulation = new Simulation();
        Scanner scanner = new Scanner(System.in);



        boolean isRunning = false;

        while (!simulation.isEnd()) {

            System.out.println("Выберите действие:");
            System.out.println("1 - Запуск / сделать шаг в симуляции");
            System.out.println("2 - Пауза / выход из паузы");
            System.out.println("3 - Выход");

            String choice = scanner.nextLine();

            Thread initalizeThread = new Thread(simulation::initializeSimulation);
            Thread runThread = new Thread(simulation::runSimulation);

            switch (choice) {
                case "1":
                    if (!simulation.isInitialized()) {
                        initalizeThread.start();

                        while (!simulation.isInitialized()) {
                            Thread.sleep(1000);
                        }
                        runThread.start();

                        isRunning = true;

                        System.out.println("Симуляция запущена!");

                    } else if (isRunning) {
                        runThread.start();
                        System.out.println("Сделан шаг в симуляции.");
                    } else {
                        System.out.println("Симуляция уже запущена.");
                    }
                    break;
                case "2":
                    if (simulation.isPaused()) {
                        simulation.resumeSimulation();
                        System.out.println("Вы успешно вышли из паузы.");
                    } else {
                        simulation.pauseSimulation();
                        System.out.println("Симуляция приостановлена.");
                    }
                    break;
                case "3":
                    System.out.println("Выход из программы.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        System.out.println("Спасибо за игру!");
    }
}
