package ru.i2dev.elevator;

import ru.i2dev.elevator.command.ElevatorCommand;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Elevator {
    private BlockingQueue<ElevatorCommand> commands;
    private ElevatorEnvironment environment;
    private ElevatorCabin cabin;
    private ElevatorControlSystem controlSystem;

    public Elevator(ElevatorEnvironment environment) {
        this.environment = environment;
        this.commands = createCommandQueue();
        this.cabin = new ElevatorCabin(environment, commands);
        this.controlSystem = new ElevatorControlSystem(environment, commands);
    }

    public void on() {
        cabin.start();
    }

    public void command(String command) throws Exception {
        controlSystem.addCommand(command);
    }

    private BlockingQueue<ElevatorCommand> createCommandQueue() {
        return new PriorityBlockingQueue<ElevatorCommand>();
    }
}
