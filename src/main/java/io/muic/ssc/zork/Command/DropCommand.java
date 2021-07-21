package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Player;
import io.muic.ssc.zork.Item.Item;

import java.util.List;
import java.util.Locale;

public class DropCommand implements Command{
    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "drop";
    }

    @Override
    public String getDescription() {
        return "Drop Item of choice that the player currently carries,";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        Player player = game.getPlayer();
        List<Item> inventory = player.getInventory();
        String itemToRemove = args.get(0).trim().toLowerCase(Locale.ROOT);

        if (args.size() < 1 || itemToRemove.equals("") ) {
            output.println("Nothing to drop! Please specify the Item you desire to drop.");
        }
        else {
            for (Item item : inventory) {
                if (item.getName().toLowerCase(Locale.ROOT).equals(itemToRemove)) {
                    inventory.remove(item);
                    output.println("");
                    output.println("You just dropped " + item.getName() + ".");
                    output.println("");
                    return;
                }
            }
            output.println("That item does not in your inventory!");
        }
    }
}
