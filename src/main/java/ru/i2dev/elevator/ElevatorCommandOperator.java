package ru.i2dev.elevator;

import ru.i2dev.elevator.command.ElevatorCommand;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class ElevatorCommandOperator {
    private BlockingQueue<ElevatorCommand> commands;

    ElevatorCommandOperator(BlockingQueue<ElevatorCommand> commands) {
        this.commands = commands;
    }

    // Если команды ехать на другой этаж нету - добавить
    // Если есть с более низким приоритетом - заменить
    // В противном случае просто не добавлять команду
    public void addOrReplace(ElevatorCommand command) {
        Iterator<ElevatorCommand> iterator = commands.iterator();
        while (iterator.hasNext()) {
            ElevatorCommand c = iterator.next();
            if (command.getFloor() == c.getFloor()) {
                if (c.compareTo(command) < 0) {
                    return;
                } else {
                    iterator.remove();
                    break; //Более одного раза ничего не попадет
                }
            }
        }
        commands.add(command);
    }

    // Если проезжая этаж внезапно оказалось, что он есть в списке,
    // Удалить его об очереди, сообщить об этом
    public boolean findAndRemoveFloorCommandIfExists(int floor) {
        Iterator<ElevatorCommand> iterator = commands.iterator();
        while (iterator.hasNext()) {
            ElevatorCommand c = iterator.next();
            if (floor == c.getFloor()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
