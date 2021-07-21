package io.muic.ssc.zork.Map;

import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Item.Item;
import io.muic.ssc.zork.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private List<String> allExits;
    private String description;
    private List<Item> allItem;
    private Monster monster;

    public Room(String description) {
        north = null;
        south = null;
        east = null;
        west = null;
        allExits = new ArrayList<>();
        this.description = description;
        allItem = new ArrayList<>();
        monster = null;
    }

    public Room getNorthExit() {
        return north;
    }

    public void setNorthExit(Room room) {
        this.north = room;
        allExits.add("north");
        north.south = this;
    }

    public Room getSouthExit() {
        return south;
    }

    public void setSouthExit(Room room) {
        this.south = room;
        allExits.add("south");
        south.north = this;
    }

    public Room getEastExit() {
        return east;
    }

    public void setEastExit(Room room) {
        this.east = room;
        allExits.add("east");
        east.west = this;
    }

    public Room getWestExit() {
        return west;
    }

    public void setWestExit(Room room) {
        this.west = room;
        allExits.add("west");
        west.east = this;
    }

    public List<String> getAllExits() {
        return allExits;
    }

    public String getDescription() {
        return description;
    }

    public void setAllItem(Item allItem) {
        this.allItem.add(allItem);
    }

    public void removeItem(Item item) {
        this.allItem.remove(item);
    }

    public List<Item> getAllItems() {
        return allItem;
    }

    public boolean containsItem() {
        return !allItem.isEmpty();
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public boolean containsMonster() {
        return monster != null;
    }

    public void removeMonster() {
        monster = null;
    }

    public void printStats() {
        GameOutput output = new GameOutput();

        output.println("");
        output.println("Current Room");
        output.println(" Exits: " + getAllExits());
        if (!allItem.isEmpty()) {
            List<String> itemList = new ArrayList<>();
            for (Item item : allItem) {
                itemList.add(item.getName());
            }
            output.println(" Item(s): " + itemList);
        }
        else {
            output.println(" Item(s): No Item can be picked up in this room.");
        }
        output.println("");
    }
}
