package ru.i2dev.elevator.data;

//Такой себе хак, но заморачиваться с системой сообщений в таком задании - перебор. имхо.
public interface ElevatorMessages {
    String FLOOR_MESSAGE = "Лифт на этаже: ";
    String DOORS_OPEN_MESSAGE = "Двери открыты";
    String DOORS_CLOSE_MESSAGE = "Двери закрыты";
}
