package ru.i2dev.elevator;

import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;
import ru.i2dev.elevator.command.ElevatorCommand;
import ru.i2dev.elevator.data.ElevatorMessages;

public class ElevatorCabin extends Thread {
    private final static Logger logger = Logger.getLogger(ElevatorCabin.class);

    private final ElevatorEnvironment environment;
    private int currentFloor = 1;
    private long betweenFloorPause;
    private long openPause;
    private BlockingQueue<ElevatorCommand> commands;
    private ElevatorCommandOperator commandOperator;

    ElevatorCabin(ElevatorEnvironment environment, BlockingQueue<ElevatorCommand> commands, ElevatorCommandOperator checker) {
        super();
        this.commands = commands;
        this.environment = environment;
        this.betweenFloorPause = calculatePause();
        this.openPause = calculateDoorsOpenPause();
        this.commandOperator = checker;
        setDaemon(false);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true) {
            try {
                ElevatorCommand command = commands.take();
                move(command);
            } catch (InterruptedException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    private void log(String action) {
        logger.info(action);
    }

    private long calculatePause() {
        return (long)((float) environment.getFloorHeight() / (float) environment.getElevatorVelocity()) * 1000L;
    }

    private long calculateDoorsOpenPause() {
        return (long)environment.getDoorsOpenTime() * 1000L;
    }

    private void moveToFlor(int floor) throws InterruptedException {
        sleep(betweenFloorPause);
        currentFloor = floor;
        log(ElevatorMessages.FLOOR_MESSAGE + floor);
    }

    private void openDoor() throws InterruptedException {
        log(ElevatorMessages.DOORS_OPEN_MESSAGE);
        sleep(openPause);
        log(ElevatorMessages.DOORS_CLOSE_MESSAGE);
    }

    private void move(ElevatorCommand command) throws InterruptedException {
        int increment = 1;
        if (command.getFloor() < currentFloor) {
            increment = -1;
        }

        while(currentFloor != command.getFloor()) {
            moveToFlor(currentFloor + increment);
            if (commandOperator.findAndRemoveFloorCommandIfExists(currentFloor)) {
                openDoor();
            }
        }
        openDoor();
    }
}