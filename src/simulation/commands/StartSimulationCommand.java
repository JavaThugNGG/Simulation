package simulation.commands;

import simulation.Launcher;

public class StartSimulationCommand implements Command {
    private final Thread simulationThread;

    public StartSimulationCommand(Thread simulationThread) {
        this.simulationThread = simulationThread;
    }

    @Override
    public void execute() {
        if (!Launcher.isSimulationStarted) {
            simulationThread.start();
            Launcher.isSimulationStarted = true;
        } else {
            System.out.println("\nОшибка! Симуляция уже запущена!\n");
        }
    }
}
