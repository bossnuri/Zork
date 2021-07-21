package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Player;
import io.muic.ssc.zork.Map.Room;
import io.muic.ssc.zork.Item.ItemFactory;
import io.muic.ssc.zork.Item.weapon.Weapon;
import io.muic.ssc.zork.Monster.Monster;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AttackWithCommand implements Command{

    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "attack with";
    }

    @Override
    public String getDescription() {
        return "Used to attack a Monster in the current room.";
    }

    @Override
    public void execute(Game game, List<String> args) {
        Player player = game.getPlayer();
        GameOutput output = game.getOutput();
        Room room = game.getCurrentRoom();
        Monster monster = room.getMonster();

        final Random RANDOM = new Random();

        String cleanedInput = args.get(0).trim().toLowerCase(Locale.ROOT);

        if (args.size() < 1 || cleanedInput.equals("")) {
            output.println("You cannot attack with nothing! Please specify your weapon.");
            output.println("type [ info ] to see your inventory.");
            output.println("");
        }
        else {
            ItemFactory factory = new ItemFactory();
            Weapon weapon = factory.createWeapon(cleanedInput);

            if (weapon == null) {    //input is not a weapon or is invalid
                output.println("That is not a weapon!");
                output.println("");
            }
            else if (!player.hasSpecificItem(cleanedInput)) {
                output.println("It seems like you don't have that Item in your inventory.");
            }
            else {
                if (!room.containsMonster()) {
                    output.println("There is no Monster around here!");
                    output.println("");
                }
                else {
                    if (monster.isAlive()) {
                        int damage = weapon.getAttackDamage() + player.getAttackDmg();
                        monster.decreaseHealth(damage);
                        output.println("");
                        output.println(monster.getName() + " took " + damage + " damage!");
                        output.println("[ " + monster.getName() + " ] HP : " + monster.getHp() + "/" + monster.getMaxHp());

                        if (!monster.isAlive()) {
                            player.setAttackPower(player.getAttackDmg() + 20);
                            room.setMonster(null);
                            output.println("The opposing " + monster.getName() + " has been defeated!");
                            output.println("Your attack damage will increase by 20 units.");
                            output.println("");
                            return;
                        }

                        if (RANDOM.nextDouble() <= monster.getAttackProbability()) {
                            player.decreaseHealth(monster.getAttackDmg());
                            output.println("!!!!! Enemy " + monster.getName() + " strikes back !!!!!");
                            output.println("");
                            output.println("You took " + monster.getAttackDmg() + " damage!");
                            output.println("HP : " + player.getHp() + "/" + player.getMax_hp());
                            output.println("");

                            if (!player.isAlive()) {
                                output.println("You fainted! Seems like the journey has ended here.");
                                output.println("Returning to the main session...");
                                output.println("");
                                game.setOutGame(true);
                                game.setInGame(false);
                            }
                        }
                        else {
                            output.println("");
                            output.println("Enemy " + monster.getName() + " tried to hit you but missed.");
                            output.println("Now it is your chance!");
                            output.println("");
                        }
                    }
                }
            }
        }
    }
}
