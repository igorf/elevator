package ru.i2dev.stuff.input.drivers;

import java.util.Scanner;

public class DriverSystemIn extends ConsoleInputDriver {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean isReady() {
        return scanner != null;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
