package simulation;

import simulation.commands.Command;

import java.util.Scanner;

public class Menu {
    private final Object printLock;
    private final Scanner scanner;
    private final Command startCommand;
    private final Command pauseCommand;
    private final Command resumeCommand;

    public Menu(Object printLock, Command startCommand, Command pauseCommand, Command resumeCommand) {
        this.printLock = printLock;
        this.scanner = new Scanner(System.in);
        this.startCommand = startCommand;
        this.pauseCommand = pauseCommand;
        this.resumeCommand = resumeCommand;
    }

    public void runMenu() {
        synchronized (printLock) {
        while (!Launcher.isExit) {
            printMenuOptions();

            int choice = getValidChoice();

            if (Launcher.isExit) {
                break;
            }

            switch (choice) {
                case 1 -> startCommand.execute();
                case 2 -> pauseCommand.execute();
                case 3 -> resumeCommand.execute();
                default -> System.out.println("Неверный выбор. Повторите ввод.");
            }
        }
    }
    }

    private void printMenuOptions() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Запуск симуляции");
        System.out.println("2 - Пауза");
        System.out.println("3 - Продолжить");
    }

    private int getValidChoice() {
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
                scanner.next();
            }
        }
    }
}
