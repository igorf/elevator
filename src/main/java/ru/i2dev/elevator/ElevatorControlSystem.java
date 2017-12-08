package ru.i2dev.elevator;

import ru.i2dev.elevator.command.ElevatorCommand;
import ru.i2dev.elevator.command.ElevatorCommandCreator;

import java.util.concurrent.BlockingQueue;

public class ElevatorControlSystem {
    private ElevatorEnvironment environment;
    private BlockingQueue<ElevatorCommand> commands;
    private ElevatorCommandCreator commandCreator;
    private long nextCommandID = 0;
    private ElevatorCommandOperator commandOperator;

    ElevatorControlSystem(ElevatorEnvironment environment, BlockingQueue<ElevatorCommand> commands, ElevatorCommandOperator checker) {
        this.commandOperator = checker;
        this.environment = environment;
        this.commands = commands;
        this.commandCreator = new ElevatorCommandCreator(environment);
    }

    public synchronized void addCommand(String command) throws Exception {
        ElevatorCommand createdCommand = commandCreator.create(command);
        createdCommand.setId(nextCommandID ++);
        commandOperator.addOrReplace(createdCommand);
    }
}
