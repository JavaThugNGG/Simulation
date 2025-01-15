package Simulation;

import java.util.Scanner;

public class Main {
    private static final Object pauseLock = new Object();
    private static final Object printLock = new Object();
    private static boolean isEnd = false;
    private static boolean isPaused = false;
    private static boolean isSimulationStarted = false;

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        Thread initializationThread = new Thread(simulation::initializeSimulation);
        initializationThread.start();

        try {
            initializationThread.join();
        } catch (InterruptedException e) {
            System.err.println("Ошибка при инициализации!");
            e.printStackTrace();
            return;
        }

        Thread simulationThread = new Thread(() -> {
            while (!isEnd) {
                synchronized (pauseLock) {
                    try {
                        while (isPaused && !isEnd) {
                            pauseLock.wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (!isEnd) {
                    simulation.startSimulation();
                }

                try {
                    Thread.sleep(1000); // Задержка для визуализации
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        runMenu(simulationThread);
    }

    private static void runMenu(Thread simulationThread) {
        Scanner scanner = new Scanner(System.in);

        while (!isEnd) {
            synchronized (printLock) {
                System.out.println("Выберите действие:");
                System.out.println("1 - Запуск симуляции");
                System.out.println("2 - Пауза");
                System.out.println("3 - Продолжить");
                System.out.println("4 - Выход");
            }

            int choice = scanner.nextInt();

            synchronized (printLock) {
                switch (choice) {
                    case 1 -> {
                        if (!isSimulationStarted) {
                            simulationThread.start();
                            isSimulationStarted = true;
                        } else {
                            System.out.println("Симуляция уже запущена.");
                        }
                    }
                    case 2 -> {
                        if (isSimulationStarted) {
                            isPaused = true;
                            System.out.println("Симуляция приостановлена.");
                        } else {
                            System.out.println("Симуляция не была запущена.");
                        }
                    }
                    case 3 -> {
                        if (isPaused) {
                            synchronized (pauseLock) {
                                isPaused = false;
                                pauseLock.notifyAll();
                                System.out.println("Симуляция продолжена.");
                            }
                        } else {
                            System.out.println("Симуляция уже запущена.");
                        }
                    }
                    case 4 -> {
                        isEnd = true;
                        synchronized (pauseLock) {
                            pauseLock.notifyAll();
                        }
                        System.out.println("Завершение программы...");
                        try {
                            simulationThread.join();
                        } catch (InterruptedException e) {
                            System.err.println("Ошибка при завершении потока симуляции.");
                            e.printStackTrace();
                        }
                    }
                    default -> System.out.println("Неверный выбор. Повторите ввод.");
                }
            }
        }
        scanner.close();
    }
}
