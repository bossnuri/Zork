package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;

import java.util.List;

public class HelpCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Print all available commands.";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();

        if (game.isInGame()) {
            List<String> cmdList = CommandFactory.getInGameCommands();

            output.println("");
            output.println("========================================================");
            output.println("");
            output.println(" [ Available In-Game Commands ]");
            for (String command : cmdList) {
                output.println("=> " + command);
                output.println("    " + CommandFactory.get(command).getDescription());
            }
            output.println("");
            output.println("========================================================");
            output.println("");
        }
        else {
            List<String> cmdList = CommandFactory.getOutGameCommands();

            output.println("========================================================");
            output.println("");
            output.println(" [ Available Start Menu Commands ]");
            for (String command : cmdList) {
                output.println("=> " + command);
                output.println("    " + CommandFactory.get(command).getDescription());
            }
            output.println("");
            output.println("========================================================");
            output.println("");
        }
    }
}
