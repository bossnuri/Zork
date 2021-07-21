package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Map.MapFactory;

import java.util.List;
import java.util.Locale;

public class PlayCommand implements Command {
    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Play new game, this command only available at when start the game.";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        String cleanedInput = args.get(0).toLowerCase(Locale.ROOT).trim();
        
        if (args.size() < 1 || args.get(0).equals("")) {
            output.println("");
            output.println("Please specify the map name you wish to play!");
            output.println("");
        }
        else {
            for (String map : MapFactory.getAvailableMap().keySet()) {
                if (map.toLowerCase(Locale.ROOT).equals(cleanedInput)) {
                    game.initiate(map);

                    output.println("");
                    output.println("game start!");
                    output.println("");
                    output.println("========================================================");
                    output.println("");

                    output.println("Welcome to " + game.getCurrentLevel().getName() + "!");
                    output.println(game.getCurrentLevel().getDescription());
                    output.println("");
                    output.println("Your goal is to " + game.getCurrentLevel().getTask() + ".");
                    output.println("");

                    game.setInGame(true);
                    game.setOutGame(false);
                    return;
                }
            }
            output.println("");
            output.println("Incorrect map name. Check your spelling!");
            output.println("");
        }
    }
}
