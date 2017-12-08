package ru.i2dev.stuff.input;

import org.apache.log4j.Logger;
import ru.i2dev.elevator.Elevator;

import java.util.Scanner;

public class ConsoleInputReader extends Thread {
    private final static Logger logger = Logger.getLogger(ConsoleInputReader.class);
    private Elevator elevator;
    private Scanner scanner;

    public ConsoleInputReader(Elevator elevator) {
        this.elevator = elevator;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (!isInterrupted()) {
            String command = scanner.nextLine();
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
        if (command.trim().equalsIgnoreCase(ConsoleCommands.EXIT_COMMAND)) {
            interrupt();
            return;
        }
        elevator.command(command);
    }
}
