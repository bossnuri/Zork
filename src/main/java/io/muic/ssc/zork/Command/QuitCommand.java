package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;

import java.util.List;

public class QuitCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "quit";
    }

    @Override
    public String getDescription() {
        return "End the current game and return to command prompt to let user choose the map or load from saved point again.";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();

        output.println("");
        output.println("Returning to the main session...");
        output.println("");
        output.println("========================================================");
        game.setInGame(false);
        game.setOutGame(true);
    }
}
