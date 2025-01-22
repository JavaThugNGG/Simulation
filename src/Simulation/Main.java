package Simulation;

import java.util.Scanner;

public class Main {
    private static final Object pauseLock = new Object();                   //статики из-за статичного main (они в нем используются)
    private static final Object printLock = new Object();

    private static boolean isSimulationStarted = false;
    private static boolean isExit = false;
    private static boolean isPaused = false;
    private static boolean isSimulationEnd = false;                               //получаем флаг из класса Simulation


    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.initializeSimulation();
        Thread simulationThread = createSimulationThread(simulation);
        runMenu(simulationThread);
    }

    private static void runMenu(Thread simulationThread) {
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            synchronized (printLock) {  // Убедимся, что вывод не конфликтует
                printMenuOptions();      // Вызов нового метода для вывода опций
            }

            int choice = getValidChoice(scanner);

            if (isExit) {                                                //после отработки симуляции цикл падает на 2 итерацию и ждет ввода (не завершает программу), это нужно чтобы завершить ее
                break;
            }

            switch (choice) {
                case 1 -> {
                    if (!isSimulationStarted) {
                        synchronized (printLock) {
                        simulationThread.start();              //здесь выводится мапа которая конфликтует в выводом меню сверху
                        }
                        isSimulationStarted = true;
                    } else {
                        System.out.println("\nОшибка! Симуляция уже запущена!\n");
                    }
                }
                case 2 -> {
                    synchronized (printLock) {
                        if (isSimulationStarted) {

                            if (!isPaused) {
                                isPaused = true;
                                System.out.println("Симуляция приостановлена.\n");
                            } else {
                                System.out.println("\nВы уже находитель в паузе!\n");
                            }

                        } else {
                            System.out.println("\nОшибка! Сначала запустите симуляцию!\n");
                        }
                    }
                }
                case 3 -> {
                    if (isPaused) {
                        synchronized (printLock) {
                            synchronized (pauseLock) {
                            isPaused = false;
                            pauseLock.notify();
                            System.out.println("Симуляция продолжена.");
                            }
                        }
                    } else {
                        System.out.println("\nОшибка! Вы уже вышли из паузы!\n");
                    }
                }
                default -> System.out.println("Неверный выбор. Повторите ввод.");
            }
        }
        scanner.close();
    }

    private static Thread createSimulationThread(Simulation simulation) {
        return new Thread(() -> {
            while (!isSimulationEnd) {                                         //цикл организован здесь а не в классе Simulation, флаг isEnd подтягивается оттуда
                synchronized (pauseLock) {
                    try {
                        while (isPaused) {
                            pauseLock.wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (!isSimulationEnd) {
                    simulation.startSimulation();                  //одна отрисовка мапы (один ход)
                }

                try {
                    Thread.sleep(1200); // Задержка для визуализации
                } catch (InterruptedException e) {
                    System.out.println("Поток simulationThread был прерван во время сна!");
                    Thread.currentThread().interrupt();
                    return;
                }
                isSimulationEnd = simulation.isEnd();              //обновили флаг (условие выхода из цикла)
            }
            simulation.printLast();                      //вывели мапу в самом конце когда волк сожрал зайца
            isExit = true;
            System.out.println("Симуляция закончена! Введите цифру от 1 до 3 для выхода");
        });
    }

    private static int getValidChoice(Scanner scanner) {
        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Неверный выбор. Введите число от 1 до 3.");
                }
            } else {
                System.out.println("Ошибка! Введите корректное число.");
                scanner.next(); // Очищаем некорректный ввод
            }
        }
    }

    private static void printMenuOptions() {
            System.out.println("Выберите действие:");
            System.out.println("1 - Запуск симуляции");
            System.out.println("2 - Пауза");
            System.out.println("3 - Продолжить");
    }
}
