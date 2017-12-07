package ru.i2dev.elevator;

import lombok.Data;

@Data
public class ElevatorEnvironment {
    private int floorNumber;
    private int floorHeight;        // meter
    private int elevatorVelocity;   // meter per second
    private int doorsOpenTime;      // seconds
}
