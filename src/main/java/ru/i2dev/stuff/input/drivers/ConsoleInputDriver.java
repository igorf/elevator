package ru.i2dev.stuff.input.drivers;

import lombok.Getter;
import lombok.Setter;

public abstract class ConsoleInputDriver {
    @Setter @Getter private ConsoleInputDriver next;
    public abstract boolean isReady();
    public abstract String read();

    public boolean hasNext() {
        return next != null;
    }
}
