package ru.i2dev.elevator.command;

import ru.i2dev.elevator.ElevatorEnvironment;
import ru.i2dev.elevator.data.CommandSource;
import ru.i2dev.elevator.data.ErrorMessages;

import java.util.HashMap;
import java.util.Map;

public class ElevatorCommandCreator {
    private ElevatorEnvironment environment;
    private static final int MIN_FLOOR = 1; //Перенести бы в окружение
    private static final Map<String, CommandSource> possibleSources = new HashMap<String, CommandSource>();
    static {
        possibleSources.put("cabin", CommandSource.CABIN);
        possibleSources.put("external", CommandSource.EXTERNAL);
    }

    public ElevatorCommandCreator(ElevatorEnvironment environment) {
        this.environment = environment;
    }

    public ElevatorCommand create(String command) throws Exception {
        String[] elements = command.split(":");
        if (elements.length != 2 || !possibleSources.containsKey(elements[0].toLowerCase().trim())) {
            throw new Exception(ErrorMessages.UNKNOWN_COMMAND);
        }

        CommandSource source = possibleSources.get(elements[0].toLowerCase().trim());

        Integer floor = Integer.valueOf(elements[1].trim());
        if (floor < MIN_FLOOR || floor > environment.getFloorNumber()) {
            throw new Exception(ErrorMessages.NO_SUCH_FLOOR + floor);
        }

        return new ElevatorCommand(floor, source);
    }
}
