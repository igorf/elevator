package ru.i2dev.stuff.input;

import org.apache.log4j.Logger;
import ru.i2dev.elevator.Elevator;
import ru.i2dev.stuff.input.drivers.ConsoleInputDataReader;

public class ConsoleInputReader extends Thread {
    private final static Logger logger = Logger.getLogger(ConsoleInputReader.class);
    private Elevator elevator;
    private static final ConsoleInputDataReader dataReader = new ConsoleInputDataReader();

    public ConsoleInputReader(Elevator elevator) {
        this.elevator = elevator;
    }

    public void run() {
        while (!isInterrupted()) {
            String command = dataReader.readLine();
            try {
                runCommand(command);
            } catch (InterruptedException ex) {
                break;
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    private void runCommand(String command) throws Exception {
        if (command == null) {
            throw new Exception(ConsoleMessages.UNABLE_TO_READ_SYSTEM_IN_MSG);
        }
        if (command.trim().equalsIgnoreCase(ConsoleCommands.EXIT_COMMAND)) {
            interrupt();
            return;
        }
        elevator.command(command);
    }
}
