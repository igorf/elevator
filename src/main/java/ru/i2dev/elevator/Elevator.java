package ru.i2dev.elevator;

import ru.i2dev.elevator.command.ElevatorCommand;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Elevator {
    private BlockingQueue<ElevatorCommand> commands;
    private ElevatorEnvironment environment;
    private ElevatorCabin cabin;
    private ElevatorControlSystem controlSystem;
    private ElevatorCommandOperator commandOperator;

    public Elevator(ElevatorEnvironment environment) {
        this.environment = environment;
        this.commands = createCommandQueue();
        this.commandOperator = new ElevatorCommandOperator(commands);
        this.cabin = new ElevatorCabin(environment, commands, commandOperator);
        this.controlSystem = new ElevatorControlSystem(environment, commands, commandOperator);
    }

    public void on() {
        cabin.start();
    }

    public void command(String command) throws Exception {
        controlSystem.addCommand(command);
    }

    private BlockingQueue<ElevatorCommand> createCommandQueue() {
        return new PriorityBlockingQueue<>();
    }
}
