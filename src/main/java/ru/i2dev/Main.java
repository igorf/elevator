package ru.i2dev;

import ru.i2dev.elevator.Elevator;
import ru.i2dev.elevator.ElevatorEnvironment;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String... args) {

        ElevatorEnvironment environment = new ElevatorEnvironment();
        environment.setDoorsOpenTime(5);
        environment.setElevatorVelocity(1);
        environment.setFloorHeight(4);
        environment.setFloorNumber(18);

        Elevator elevator = new Elevator(environment);
        elevator.on();
        try {
            elevator.command("external:6");
            sleep(2500);
            elevator.command("external:14");
            elevator.command("cabin:2");
            elevator.command("external:11");
            elevator.command("cabin:8");
            elevator.command("cabin:3");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
