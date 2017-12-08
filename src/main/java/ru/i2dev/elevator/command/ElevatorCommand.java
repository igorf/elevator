package ru.i2dev.elevator.command;

import lombok.Data;
import ru.i2dev.elevator.data.CommandSource;

@Data
public class ElevatorCommand implements Comparable<ElevatorCommand> {
    Long id;
    int floor;
    CommandSource source;

    ElevatorCommand(int floor, CommandSource source) {
        this.floor = floor;
        this.source = source;
    }

    public int compareTo(ElevatorCommand o) {
        int compared = source.compareTo(o.source);
        if (compared == 0) {
            compared = id.compareTo(o.id);
        }

        return compared;
    }
}
