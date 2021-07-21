package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Item.Item;
import io.muic.ssc.zork.Item.ItemFactory;
import io.muic.ssc.zork.Item.potion.Potion;
import io.muic.ssc.zork.Player;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ApplyCommand implements Command{
    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "apply";
    }

    @Override
    public String getDescription() {
        return "Apply the desired item in your inventory. This command only available while playing game";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        Player player = game.getPlayer();
        List<Item> inventory = player.getInventory();
        String itemToApply = args.get(0).toLowerCase(Locale.ROOT).trim();

        if (args.size() < 1 || itemToApply.equals("")) {
            output.println("");
            output.println("You cannot apply nothing! Please specify your item.");
            output.println("type [ info ] to see your inventory.");
            output.println("");
        }
        else {
            ItemFactory factory = new ItemFactory();
            Potion potion = factory.createPotion(itemToApply);

            if (potion == null) {           //input is not a potion or is invalid
                output.println("");
                output.println("You cannot apply this item to yourself!");
                output.println("");
            }
            else if (!player.hasSpecificItem(itemToApply)) {
                output.println("");
                output.println("That item does not present in your inventory!");
                output.println("");
            }
            else if (player.getHp() == player.getMax_hp()) {
                output.println("");
                output.println("Your current HP is full! Better save it for later.");
                output.println("");
            }
            else {
                Iterator<Item> iter = inventory.iterator();
                while (iter.hasNext()) {      //use iterator to prevent ConcurrentModificationException
                    Item item = iter.next();
                    if (item.getName().toLowerCase(Locale.ROOT).equals(itemToApply)) {
                        player.increaseHealth(potion.getIncreaseHp());
                        iter.remove();
                        output.println("");
                        output.println("You just applied " + potion.getName() + " to yourself.");
                        output.println("HP: " + player.getHp() + "/" + player.getMax_hp());
                        output.println("");
                        return;
                    }
                }
            }
        }
    }
}
