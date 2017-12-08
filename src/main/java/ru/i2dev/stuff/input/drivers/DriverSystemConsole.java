package ru.i2dev.stuff.input.drivers;

import java.io.Console;

public class DriverSystemConsole extends ConsoleInputDriver {
    private Console console = System.console();

    @Override
    public boolean isReady() {
        return console != null;
    }

    @Override
    public String read() {
        return console.readLine();
    }
}
