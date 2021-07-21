package io.muic.ssc.zork.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class CommandFactory {

    private static final Set<String> INGAME_COMMANDS = new HashSet<>();
    private static final Set<String> OUTGAME_COMMANDS = new HashSet<>();

    private static final Map<String, Command> COMMANDS_MAP = new HashMap<>() {{
        CommandType[] commandTypes = CommandType.values();
        for (int i = 0; i < commandTypes.length; i++) {
            if (commandTypes[i].isInGame()) {
                INGAME_COMMANDS.add(commandTypes[i].getCommandName());
            }
            if (commandTypes[i].isOutGame()) {
                OUTGAME_COMMANDS.add(commandTypes[i].getCommandName());
            }
            try {
                Command command = commandTypes[i].getCommandClass().getDeclaredConstructor().newInstance();
                put(commandTypes[i].getCommandName(), command);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }};

    public static Command get(String command) {
        return COMMANDS_MAP.getOrDefault(command, null);
    }

    public static List<String> getAllCommands() {
        return COMMANDS_MAP.keySet().stream().collect(Collectors.toList());
    }

    public static List<String> getInGameCommands() {
        List<String> cmdList = new ArrayList<>(INGAME_COMMANDS);
        return cmdList;
    }

    public static List<String> getOutGameCommands() {
        List<String> cmdList = new ArrayList<>(OUTGAME_COMMANDS);
        return cmdList;
    }
}
