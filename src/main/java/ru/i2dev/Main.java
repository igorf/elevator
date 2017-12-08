package ru.i2dev;

import org.apache.log4j.Logger;
import ru.i2dev.elevator.Elevator;
import ru.i2dev.elevator.ElevatorEnvironment;
import ru.i2dev.stuff.env.EnvironmentCreator;
import ru.i2dev.stuff.input.ConsoleInputReader;

public class Main {
    // Default args -v=1 -h=4 -f=18 -p=5
    private final static Logger logger = Logger.getLogger(Main.class);
    private static EnvironmentCreator envCreator;

    public static void main(String... args) {
        try {
            envCreator = new EnvironmentCreator(args);
            ElevatorEnvironment environment = envCreator.create();
            Elevator elevator = new Elevator(environment);
            new ConsoleInputReader(elevator).start();

            elevator.on();
        } catch (org.apache.commons.cli.ParseException ex) {
            logger.error(ex.getMessage());
            envCreator.help();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
