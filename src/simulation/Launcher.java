package simulation;

import simulation.commands.Command;
import simulation.commands.PauseSimulationCommand;
import simulation.commands.ResumeSimulationCommand;
import simulation.commands.StartSimulationCommand;

public class Launcher {
    private static final Object pauseLock = new Object();
    private static final Object printLock = new Object();

    public static boolean isSimulationStarted = false;
    public static boolean isExit = false;
    public static boolean isPaused = false;
    public static boolean isSimulationEnd = false;

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.initializeSimulation();
        Thread simulationThread = createSimulationThread(simulation);

        Command startCommand = new StartSimulationCommand(simulationThread);
        Command pauseCommand = new PauseSimulationCommand(printLock);
        Command resumeCommand = new ResumeSimulationCommand(printLock, pauseLock);

        Menu menu = new Menu(printLock, startCommand, pauseCommand, resumeCommand);
        menu.runMenu();

    }

    private static Thread createSimulationThread(Simulation simulation) {
        return new Thread(() -> {
            while (!isSimulationEnd) {
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
                    simulation.startSimulation();
                }

                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    System.out.println("Поток simulationThread был прерван во время сна!");
                    Thread.currentThread().interrupt();
                    return;
                }
                isSimulationEnd = simulation.isEnd();
            }
            simulation.printLast();
            isExit = true;
            System.out.println("Симуляция закончена! Введите цифру от 1 до 3 для выхода");
        });
    }
}
