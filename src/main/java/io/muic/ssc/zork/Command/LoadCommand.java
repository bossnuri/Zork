package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;
import io.muic.ssc.zork.Map.MapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class LoadCommand implements Command {
    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "load";
    }

    @Override
    public String getDescription() {
        return "Load game state from saved point, this command only available when start the game.";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        String fileName = args.get(0);

        if (args.size() < 1 || fileName.equals("")) {
            output.println("");
            output.println("Please specify your file name!");
            output.println("");
        }
        else {
            File file = new File("C://Users//Settawut//Desktop//saveZork//" + fileName + ".txt");
            try {
                Scanner scanner = new Scanner(file);
                CommandParser commandParser = new CommandParser();

                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    List<String> words = commandParser.parse(s);
                    if (words != null) {                                        //to prevent null exception from whitespaces in file
                        Command command = CommandFactory.get(words.get(0));
                        if (command != null) {
                            if (command.getCommand().equals("play") && MapFactory.get(words.get(1)) != null) {
                                game.setCurrentLevel(words.get(1));
                            }
                            command.execute(game, words.subList(1, words.size()));
                        }
                    }
                }
                game.setInGame(true);
                game.setOutGame(false);

                output.println("");
                output.println("Save loaded!");
                output.println("");
            }
            catch (FileNotFoundException e) {
                output.println("Cannot load [ " + fileName + " ] due to invalid save name!");
            }
        }
    }
}
