package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Player;
import io.muic.ssc.zork.Map.Room;
import io.muic.ssc.zork.Monster.Monster;

import java.util.List;

public class InfoCommand implements Command {

    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Print out information of the player and the room that the player is currently in.";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        Player player = game.getPlayer();
        Room room = game.getCurrentRoom();
        Monster monster = room.getMonster();

        output.println("");
        player.printStats();
        room.printStats();
        if (room.containsMonster()) {
            monster.printStats();
        }
        else {
            output.println("[ No monster can be seen around the room ]");
        }
        output.println("");
    }
}
