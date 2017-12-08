package ru.i2dev.stuff.env;

import org.apache.commons.cli.*;
import ru.i2dev.elevator.ElevatorEnvironment;

public class EnvironmentCreator {
    private String[] args;
    private Options options;
    private static final HelpFormatter helpFormatter = new HelpFormatter();

    private static final String COMMAND_LINE = "<program> <args>";

    private static final String FN_ARG = "floors-number";
    private static final String VL_ARG = "velocity";
    private static final String FH_ARG = "floor-height";
    private static final String DP_ARG = "door-open-pause";

    public EnvironmentCreator(String... args) {
        this.args = args;
        this.options = createOptions();
    }

    public ElevatorEnvironment create() throws Exception {
        ElevatorEnvironment result = new ElevatorEnvironment();
        CommandLine commandLine = parse();

        result.setDoorsOpenTime(Integer.valueOf(commandLine.getOptionValue(DP_ARG)));
        result.setElevatorVelocity(Integer.valueOf(commandLine.getOptionValue(VL_ARG)));
        result.setFloorHeight(Integer.valueOf(commandLine.getOptionValue(FH_ARG)));
        result.setFloorNumber(Integer.valueOf(commandLine.getOptionValue(FN_ARG)));

        return result;
    }

    public void help() {
        helpFormatter.printHelp(COMMAND_LINE, options);
    }

    private Options createOptions() {
        Options options = new Options();
        options.addRequiredOption( "f", FN_ARG, true, "Количество этажей" );
        options.addRequiredOption( "v", VL_ARG, true, "Скорость лифта (м/с)" );
        options.addRequiredOption( "h", FH_ARG, true, "Высота этажа (м)" );
        options.addRequiredOption( "p", DP_ARG, true, "Пауза до закрытия дверей (с)" );

        return options;
    }

    private CommandLine parse() throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }
}
