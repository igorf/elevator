package ru.i2dev.elevator.command;

import lombok.Data;

@Data
public class ElevatorCommand {
    int floor;

    ElevatorCommand(int floor) {
        this.floor = floor;
    }
}
