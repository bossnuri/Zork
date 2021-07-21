package io.muic.ssc.zork.Map;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Item.ItemFactory;
import io.muic.ssc.zork.Item.potion.Potion;
import io.muic.ssc.zork.Item.weapon.Weapon;
import io.muic.ssc.zork.Monster.Monster;
import io.muic.ssc.zork.Monster.MonsterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kimochi extends Map{

    private GameOutput output = new GameOutput();
    private List<Room> roomList = new ArrayList<>();

    public Kimochi() {
        setName("Kimochi");
        setDescription("Welcome to the simple and friendly place... I hope you enjoy this place");
        setTask("go eliminating all monsters and gathering all items");

        ItemFactory itemFactory = new ItemFactory();
        MonsterFactory monsterFactory = new MonsterFactory();

        Room room1 = new Room("Entrance");
        roomList.add(room1);
        Room room2 = new Room("Kitchen of light");
        roomList.add(room2);
        Room room3 = new Room("Library");
        roomList.add(room3);
        Room room4 = new Room("Fitness");
        roomList.add(room4);
        Room room5 = new Room("Cinema");
        roomList.add(room5);
        Room room6 = new Room("Play Ground");
        roomList.add(room6);
        Room room7 = new Room("Gaming Room");
        roomList.add(room7);

        room1.setNorthExit(room2);
        room2.setSouthExit(room1);
        room2.setWestExit(room3);
        room2.setEastExit(room4);
        room3.setEastExit(room2);
        room3.setNorthExit(room5);
        room5.setSouthExit(room3);
        room4.setWestExit(room2);
        room4.setNorthExit(room6);
        room6.setSouthExit(room4);
        room6.setEastExit(room7);
        room7.setWestExit(room6);

        setStartRoom(room1);

        Weapon sword = itemFactory.createWeapon("Sword");
        Weapon rifle = itemFactory.createWeapon("Rifle");
        Potion megaPotion = itemFactory.createPotion("Mega Potion");
        Potion smallPotion = itemFactory.createPotion("Small Potion");


        Monster earthShaker = monsterFactory.createMonster("Earth Shaker");
        Monster earthShaker2 = monsterFactory.createMonster("Earth Shaker");
        Monster medusa = monsterFactory.createMonster("Medusa");
        Monster mokeyKing = monsterFactory.createMonster("Monkey King");
        Monster medusa2 = monsterFactory.createMonster("Medusa");

        room1.setAllItem(sword);
        room1.setAllItem(smallPotion);
        room3.setAllItem(rifle);
        room3.setAllItem(smallPotion);
        room6.setAllItem(megaPotion);

        room2.setMonster(medusa);
        room3.setMonster(earthShaker);
        room4.setMonster(earthShaker2);
        room5.setMonster(mokeyKing);
        room7.setMonster(medusa2);
    }

    @Override
    public boolean taskComplete() {
        for (Room room : roomList) {
            if (room.containsItem() || room.containsMonster()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void printMap() {
        File file = new File("C:/Users/user/Desktop/layout/GraveyardLayout.txt");
        try {
            Scanner scanner = new Scanner(file);
            output.println("");
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                output.println(s);
            }
            output.println("");
        }
        catch (FileNotFoundException e) {
            output.println("");
            output.println("!!! An error occurred while loading the map !!!");
            output.println("");
        }
    }
}
