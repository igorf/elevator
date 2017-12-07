package ru.i2dev.elevator.command;

import ru.i2dev.elevator.ElevatorEnvironment;
import ru.i2dev.elevator.data.ErrorMessages;

public class ElevatorCommandCreator {
    private ElevatorEnvironment environment;
    private static final int MIN_FLOOR = 1; //Перенести бы в окружение

    public ElevatorCommandCreator(ElevatorEnvironment environment) {
        this.environment = environment;
    }

    public ElevatorCommand create(String command) throws Exception {
        Integer floor = Integer.valueOf(command.trim());
        if (floor < MIN_FLOOR || floor > environment.getFloorNumber()) {
            throw new Exception(ErrorMessages.NO_SUCH_FLOOR + floor);
        }

        return new ElevatorCommand(floor);
    }
}
