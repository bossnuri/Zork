package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Player;
import io.muic.ssc.zork.Map.Room;

import java.util.List;
import java.util.Locale;

public class GoCommand implements Command{
    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "go";
    }

    @Override
    public String getDescription() {
        return "Move player to the room as specified by the direction, e.g. north, east, west, and south." ;
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        Player player = game.getPlayer();
        Room room = game.getCurrentRoom();
        Room currentRoom = game.getCurrentRoom();
        String direction = args.get(0).trim().toLowerCase(Locale.ROOT);

        if (args.size() < 1 || direction.equals("")) {
            output.println("Please specify the direction you want to move!");
        }
        else {
            if (room.getAllExits().contains(direction)) {
                if (direction.equals("north")) {
                    game.moveRoom(room.getNorthExit());
                    currentRoom = room.getNorthExit();
                }
                else if (direction.equals("south")) {
                    game.moveRoom(room.getSouthExit());
                    currentRoom = room.getSouthExit();
                }
                else if (direction.equals("east")) {
                    game.moveRoom(room.getEastExit());
                    currentRoom = room.getEastExit();
                }
                else {
                    game.moveRoom(room.getWestExit());
                    currentRoom = room.getWestExit();
                }
                player.increaseHealth(100);
                output.println("");
                output.println("You have successfully moved to the room at " + direction + ".");
                output.println("Now, you are currently at " + currentRoom.getDescription() + ".");
                output.println("Your HP will recover by 100 HP.");
                output.println("");
            }
            else {
                output.println("");
                output.println("There's no exit there!");
                output.println("Type [ info ] to get available exit(s).");
                output.println("");
            }
        }
    }
}
