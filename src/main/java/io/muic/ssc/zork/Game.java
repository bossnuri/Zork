package io.muic.ssc.zork;

import io.muic.ssc.zork.Command.Command;
import io.muic.ssc.zork.Command.CommandFactory;
import io.muic.ssc.zork.Command.CommandParser;
import io.muic.ssc.zork.Map.Map;
import io.muic.ssc.zork.Map.MapFactory;
import io.muic.ssc.zork.Map.Room;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Game {

    private GameOutput output = new GameOutput();
    private CommandParser commandParser = new CommandParser();
    private static Player player = new Player();
    private static Room currentRoom;
    public static Map currentLevel;
    private boolean isRunning = true;
    private boolean inGame = false;
    private boolean outGame = true;
    private StringBuilder inputTracker = new StringBuilder();

    public void run() {
        while (isRunning) {
            if (isOutGame()) {
                welcomeScreen();
            }
            else {
                Scanner in = new Scanner(System.in);
                String s = in.nextLine();
                List<String> words = commandParser.parse(s, isInGame());
                if (words != null) {
                    Command command = CommandFactory.get(words.get(0));
                    String cmdName = command.getCommand();
                    if (command != null) {
                        command.execute(this, words.subList(1, words.size()));
                        if (!cmdName.equals("save") && !cmdName.equals("load") && !cmdName.equals("quit")) {
                            addInputTracker(s);
                        }
                    }
                    if (currentLevel.taskComplete()) {
                        output.println("");
                        output.println("********************************************************");
                        output.println("                 Objective Complete                     ");
                        output.println("********************************************************");
                        output.println("");
                        setInGame(false);
                        setOutGame(true);
                    }
                }
                else {
                    output.println("");
                    output.println("[ " + s + " ] is not a valid command!");
                    output.println("");
                }
            }
        }
    }


    public void welcomeScreen() {
        output.println("");
        output.println("         Welcome to Zork Game!");
        System.out.println("");
        output.println("          Choose the Commands");
        for (String cmd : CommandFactory.getOutGameCommands()) {
            output.println("           -  [ " + cmd + " ]");
        }
        output.println("");
        output.println("            Maps");
        for (String map : MapFactory.getAvailableMap().keySet()) {
            output.println("           -  [ " + map + " ]");
        }
        output.println("");

        createNewInputTracker();

        while (isOutGame() == true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            List<String> words = commandParser.parse(s, isInGame());
            if (words != null) {
                Command command = CommandFactory.get(words.get(0));
                if (command != null) {
                    command.execute(this, words.subList(1, words.size()));
                    if (command.getCommand().equals("play")) {
                        if (isInGame()) {
                            addInputTracker(s);
                        }
                    }
                }
            }
            else {
                output.println("");
                output.println("[ " + s + " ] is not a valid command!");
                output.println("");
            }
        }
    }

    public GameOutput getOutput() {
        return output;
    }

    public void exit() {
        System.exit(0);
    }

    public static Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveRoom(Room room) {
        currentRoom = room;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isOutGame() {
        return outGame;
    }

    public void setOutGame(boolean outGame) {
        this.outGame = outGame;
    }

    public void addInputTracker(String input) {
        inputTracker.append(input);
        inputTracker.append("\n");
    }

    public void createNewInputTracker() {
        inputTracker = new StringBuilder();
    }

    public String getInputTrackerAsString() {
        return inputTracker.toString();
    }

    public static Map getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        for (String level : MapFactory.getAvailableMap().keySet()) {
            if (currentLevel.toLowerCase(Locale.ROOT).equals(level.toLowerCase(Locale.ROOT))) {
                MapFactory factory = new MapFactory();
                this.currentLevel = factory.createMap(level);
                this.currentRoom = this.currentLevel.getStartRoom();
            }
        }
    }

    public void initiate(String mapName) {
        player = new Player();
        setCurrentLevel(mapName);
    }
}
