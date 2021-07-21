package io.muic.ssc.zork.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CommandParser {

    private List<String> allInGameCommandSortedByLength = CommandFactory.getInGameCommands();
    {
        allInGameCommandSortedByLength.sort(((o1, o2) -> o2.length() - o1.length()));
    }

    private List<String> allMenuCommandSortedByLength = CommandFactory.getOutGameCommands();
    {
        allMenuCommandSortedByLength.sort(((o1, o2) -> o2.length() - o1.length()));
    }

    private List<String> allCommandSortedByLength = CommandFactory.getAllCommands();
    {
        allMenuCommandSortedByLength.sort(((o1, o2) -> o2.length() - o1.length()));
    }

    private String matchInputToCommand(String input, Boolean inGame) {
        if (inGame) {
            for (String command : allInGameCommandSortedByLength) {
                if (input.startsWith(command)) {
                    return command;
                }
            }
        }
        else {
            for (String command : allMenuCommandSortedByLength) {
                if (input.startsWith(command)) {
                    return command;
                }
            }
        }
        return null;
    }

    private String matchInputToCommand(String input) {
        for (String command : allCommandSortedByLength) {
            if (input.startsWith(command)) {
                return command;
            }
        }
        return null;
    }


    // "attack with weapon" -> ["attack with", "weapon"]
    public List<String> parse(String stringInput, Boolean inGame) {
        try {
            String cleanedInput = stringInput.toLowerCase(Locale.ROOT).trim();
            String cmd = matchInputToCommand(cleanedInput, inGame);
            String argString = cleanedInput.substring(cmd.length()).trim();
            return Arrays.asList(cmd, argString);
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public List<String> parse(String stringInput) {
        try {
            String cleanedInput = stringInput.toLowerCase(Locale.ROOT).trim();
            String cmd = matchInputToCommand(cleanedInput);
            String argString = cleanedInput.substring(cmd.length()).trim();
            return Arrays.asList(cmd, argString);
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}
