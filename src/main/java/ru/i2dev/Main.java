package ru.i2dev;

import org.apache.log4j.Logger;
import ru.i2dev.elevator.Elevator;
import ru.i2dev.elevator.ElevatorEnvironment;
import ru.i2dev.stuff.env.EnvironmentCreator;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String... args) {
        try {
            ElevatorEnvironment environment = new EnvironmentCreator(args).create();
            Elevator elevator = new Elevator(environment);
            elevator.on();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        /*
        try {
            elevator.command("external:6");
            sleep(6000);
            elevator.command("external:14");
            elevator.command("cabin:14");
            sleep(14100);
            elevator.command("cabin:2");
            elevator.command("cabin:1");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        */
    }
}
