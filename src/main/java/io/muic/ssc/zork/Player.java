package io.muic.ssc.zork;

import io.muic.ssc.zork.Item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Player {

    private int max_hp;
    private int hp;
    private int attackDmg;
    private boolean alive;
    private List<Item> inventory;

    public Player() {
        max_hp = 1000;
        hp = max_hp;
        attackDmg = 75;
        alive = true;
        inventory = new ArrayList<>();
    }

    public void increaseHealth(int hpToGain) {
        if (hp + hpToGain >= max_hp) {
            hp = max_hp;
        }
        else {
            hp += hpToGain;
        }
    }

    public void decreaseHealth(int attackDmg) {
        hp -= attackDmg;
        if (hp <= 0) {
            hp = 0;
            alive = false;
        }
    }

    public int getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
    }

    public int getHp() {
        return hp;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackPower(int attackDmg) {
        this.attackDmg = attackDmg;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Boolean hasSpecificItem(String itemToGet) {
        for (Item item : inventory) {
            if (item.getName().toLowerCase(Locale.ROOT).equals(itemToGet)) {
                return true;
            }
        }
        return false;
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
    }

    public void printStats() {
        GameOutput output = new GameOutput();
        output.println("");
        output.println(" Player's Stats ");
        output.println("-> HP: " + hp + "/" + max_hp);
        output.println("-> Attack DMG: " + attackDmg);
        output.println(" Inventory");

        if (inventory.isEmpty()) {
            output.println("Nothing Here");
        }
        else {
            for (Item item : inventory) {
                output.println("=> " + item.getName());
                output.println(" " + item.getDescription());
            }
            output.println("");
        }
    }
}
