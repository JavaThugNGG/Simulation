package Simulation;

import java.util.Scanner;

public class Main {
    private static final Object pauseLock = new Object();                   //статики из-за статичного main (они в нем используются)
    private static final Object printLock = new Object();

    private static boolean isSimulationStarted = false;
    private static boolean isExit = false;
    private static boolean isPaused = false;
    private static boolean isEnd = false;                               //получаем флаг из класса Simulation


    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        simulation.initializeSimulation();

        Thread simulationThread = createSimulationThread(simulation);

        runMenu(simulationThread);
    }

    private static void runMenu(Thread simulationThread) {
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            synchronized (printLock) {                          //println бывает конфликтует с выводом мапы (текст назжает на мапу)
                System.out.println("Выберите действие:");
                System.out.println("1 - Запуск симуляции");
                System.out.println("2 - Пауза");
                System.out.println("3 - Продолжить");
            }


            int choice;                                     //защита от дурака
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        System.out.println("Неверный выбор. Введите число от 1 до 3.");
                    }
                } else {
                    System.out.println("Ошибка! Введите корректное число.");
                    scanner.next();
                }
            }

            if (isExit) {           //после отработки симуляции цикл падает на 2 итерацию и ждет ввода (не завершает программу), это нужно чтобы завершить ее
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
                        if (isSimulationStarted) {
                            isPaused = true;
                            synchronized (printLock) {
                                System.out.println("Симуляция приостановлена.");
                            }
                        } else {
                            System.out.println("\nОшибка! Сначала запустите симуляцию!\n");
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
            while (!isEnd) {                                         //цикл организован здесь а не в классе Simulation, флаг isEnd подтягивается оттуда
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

                if (!isEnd) {
                    simulation.startSimulation();                  //одна отрисовка мапы (один ход)
                }

                try {
                    Thread.sleep(1200); // Задержка для визуализации
                } catch (InterruptedException e) {
                    System.out.println("Поток simulationThread был прерван во время сна!");
                    Thread.currentThread().interrupt();
                    return;
                }
                isEnd = simulation.isEnd();              //обновили флаг (условие выхода из цикла)
            }
            simulation.printLast();                      //вывели мапу в самом конце когда волк сожрал зайца
            isExit = true;
            System.out.println("Симуляция закончена! Введите цифру от 1 до 3 для выхода");
        });
    }
}
