package ru.i2dev.stuff.input.drivers;

public class ConsoleInputDataReader {
    private ConsoleInputDriver root = new DriverSystemConsole(); //Default
    private ConsoleInputDriver currentDriver;

    public ConsoleInputDataReader() {
        root.setNext(new DriverSystemIn());
        currentDriver = findFirstReadyDriver();
    }

    public String readLine() {
        if (currentDriver != null) {
            return currentDriver.read();
        }
        return null;
    }

    private ConsoleInputDriver findFirstReadyDriver() {
        ConsoleInputDriver d = root;
        while (d != null && !d.isReady() && d.hasNext()) {
            d = d.getNext();
        }
        return (d != null && d.isReady()) ? d : null;
    }
}
