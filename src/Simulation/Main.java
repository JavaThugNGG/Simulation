package Simulation;

import java.util.Scanner;

public class Main {
    private static boolean isPaused = false;
    boolean isRunning = false;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.game();
    }

    public void game() throws InterruptedException {
        System.out.println("Добро пожаловать в симуляцию!\n");

        Simulation simulation = new Simulation();
        Scanner scanner = new Scanner(System.in);

        Thread initalizeThread = new Thread(simulation::initializeSimulation);

        System.out.println("Выберите действие:");
        System.out.println("1 - Запуск / сделать шаг в симуляции");
        System.out.println("2 - Пауза / выход из паузы");
        System.out.println("3 - Выход");

        Runnable run = new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                while (true) {
                    simulation.runSimulation();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (Main.this) {
                        while (isPaused) {
                            try {
                                Main.this.wait(); // Ожидаем, пока не выйдем из паузы
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        };

        // Создаем поток для симуляции
        Thread runThread = new Thread(run);

        while (!simulation.isEnd()) {
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (!simulation.isInitialized()) {
                        initalizeThread.start();

                        while (!simulation.isInitialized()) {
                            Thread.sleep(500);
                        }
                        runThread.start();

                        System.out.println("Симуляция запущена!");
                    } else if (!isRunning) {
                        runThread.start();
                        System.out.println("Симуляция запущена");
                    } else if (isRunning) {
                        System.out.println("Симуляция уже запущена.");
                    }
                    break;

                case "2":
                    synchronized (Main.this) {  // Синхронизируем доступ к isPaused
                        if (isPaused) {
                            isPaused = false;
                            Main.this.notifyAll(); // Уведомляем все потоки, что пауза снята
                            System.out.println("Вы успешно вышли из паузы.");
                        } else {
                            isPaused = true;
                            System.out.println("Симуляция приостановлена.");
                        }
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
