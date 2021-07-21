package io.muic.ssc.zork.Map;

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

public class Hell extends Map {

    private GameOutput output = new GameOutput();
    private List<Room> roomList = new ArrayList<>();

    public Hell() {
        setName("Hello");
        setDescription("Welcome all bad People, we have set the fire and monsters for u..");
        setTask("eliminate all monsters");

        ItemFactory itemFactory = new ItemFactory();
        MonsterFactory monsterFactory = new MonsterFactory();

        Room room1 = new Room("Hell Pan");
        roomList.add(room1);
        Room room2 = new Room("Under Fire");
        roomList.add(room2);
        Room room3 = new Room("Earth Shaker Bed Room");
        roomList.add(room3);
        Room room4 = new Room("Torture Room");
        roomList.add(room4);
        Room room5 = new Room("The darkest Zone");
        roomList.add(room5);

        room1.setWestExit(room2);
        room2.setEastExit(room1);
        room2.setNorthExit(room3);
        room3.setSouthExit(room2);
        room3.setEastExit(room4);
        room4.setWestExit(room3);
        room4.setNorthExit(room5);

        setStartRoom(room1);

        Weapon sword = itemFactory.createWeapon("sword");
        Weapon gun = itemFactory.createWeapon("shot gun");
        Potion medPotion = itemFactory.createPotion("medium potion");
        Potion megaPotion = itemFactory.createPotion("mega potion");

        Monster earthShaker = monsterFactory.createMonster("Earth Shaker");
        Monster monkeyKing = monsterFactory.createMonster("Monkey King");
        Monster queenOfPain = monsterFactory.createMonster("Queen of Pain");
        Monster medusa = monsterFactory.createMonster("Medusa");

        room1.setAllItem(sword);
        room2.setAllItem(medPotion);
        room4.setAllItem(megaPotion);
        room5.setAllItem(gun);

        room2.setMonster(monkeyKing);
        room3.setMonster(earthShaker);
        room4.setMonster(queenOfPain);
        room5.setMonster(medusa);
    }

    @Override
    public boolean taskComplete() {
        for (Room room : roomList) {
            if (room.containsMonster()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void printMap() {
        File file = new File("C:/Users/user/Desktop/layout/ColosseumLayout.txt");
        try {
            Scanner scanner = new Scanner(file);
            output.println("");
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                output.println(s);
            }
            output.println("");
        } catch (FileNotFoundException e) {
            output.println("");
            output.println("!!! An error occurred while loading the map !!!");
            output.println("");
        }
    }
}
