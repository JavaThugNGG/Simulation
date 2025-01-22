package simulation.commands;

import simulation.Launcher;

public class ResumeSimulationCommand implements Command {
    private final Object printLock;
    private final Object pauseLock;

    public ResumeSimulationCommand(Object printLock, Object pauseLock) {
        this.printLock = printLock;
        this.pauseLock = pauseLock;
    }

    @Override
    public void execute() {
        if (Launcher.isPaused) {
            synchronized (printLock) {
                synchronized (pauseLock) {
                    Launcher.isPaused = false;
                    pauseLock.notify();
                    System.out.println("Симуляция продолжена.");
                }
            }
        } else {
            System.out.println("\nОшибка! Вы уже вышли из паузы!\n");
        }
    }
}
