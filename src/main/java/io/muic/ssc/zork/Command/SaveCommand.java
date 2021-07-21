package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.GameOutput;

import java.io.*;
import java.util.List;

public class SaveCommand implements Command{
    @Override
    public int numArgs() {
        return 1;
    }

    @Override
    public String getCommand() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Load game state from saved point, this command only available at when start the game." +
                "\n    To save your current session, please use the same name as your last saved point." +
                "\n    If this is your first time saving this session, feel free to use any name! ";
    }

    @Override
    public void execute(Game game, List<String> args) {
        GameOutput output = game.getOutput();
        String fileName = args.get(0);

        File file = new File("C://Users//Settawut//Desktop//saveZork//" + fileName + ".txt");

        if (args.size() < 1 || fileName.equals("")) {
            output.println("");
            output.println("Please specify your save name!");
            output.println("");
        }
        else {
            try {
                if (!file.createNewFile()) {      //check if the filename already exists. If so, overwrite it.
                    FileWriter fw = new FileWriter("C://Users//Settawut//Desktop//saveZork//" + fileName + ".txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(game.getInputTrackerAsString());
                    game.createNewInputTracker();
                    pw.flush();

                    pw.close();
                    bw.close();
                    fw.close();
                    output.println("");
                    output.println("You file has been overwritten");
                    output.println("");
                }
                else {                            //if not, create a new text file and keep the data in it
                    output.println("Now saving...");
                    FileWriter saveFile = new FileWriter("C://Users//Settawut//Desktop//saveZork//" + fileName + ".txt");
                    saveFile.write(game.getInputTrackerAsString());
                    game.createNewInputTracker();
                    saveFile.close();
                    output.println("");
                    output.println("Your game is saved.");
                    output.println("");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
