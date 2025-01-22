package simulation.commands;

import simulation.Launcher;

public class PauseSimulationCommand implements Command {
    private final Object printLock;

    public PauseSimulationCommand(Object printLock) {
        this.printLock = printLock;
    }

    @Override
    public void execute() {
        synchronized (printLock) {
            if (Launcher.isSimulationStarted) {
                if (!Launcher.isPaused) {
                    Launcher.isPaused = true;
                    System.out.println("Симуляция приостановлена.\n");
                } else {
                    System.out.println("\nВы уже находитесь в паузе!\n");
                }
            } else {
                System.out.println("\nОшибка! Сначала запустите симуляцию!\n");
            }
        }
    }
}
