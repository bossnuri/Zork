package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Player;
import io.muic.ssc.zork.Map.Room;
import io.muic.ssc.zork.Item.Item;

import java.util.List;
import java.util.Locale;

public class TakeCommand implements Command{
    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "take";
    }

    @Override
    public String getDescription() {
        return "Take command is used to pick up the Item in the current room." ;
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        Player player = game.getPlayer();
        Room room = game.getCurrentRoom();

        String itemToTake = args.get(0).trim().toLowerCase(Locale.ROOT);

        if (args.size() < 1 || itemToTake.equals("")) {
            output.println("");
            output.println("You cannot just take nothing! Please specify the Item you desire to take.");
            output.println("");
        }
        else {
            for (Item item : room.getAllItems()) {
                if (item.getName().toLowerCase(Locale.ROOT).equals(itemToTake)) {
                    player.pickUpItem(item);
                    room.removeItem(item);
                    output.println("");
                    output.println("You just picked up " + item.getName() + "!");
                    output.println("");
                    return;
                }
            }
            output.println("");
            output.println("[ " + args.get(0) + " ] cannot be found in this room.");
            output.println("");
        }
    }
}
