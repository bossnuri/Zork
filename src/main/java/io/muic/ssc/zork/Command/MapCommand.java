package io.muic.ssc.zork.Command;

import io.muic.ssc.zork.Game;
import io.muic.ssc.zork.Map.Map;

import java.util.List;


public class MapCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "map";
    }

    @Override
    public String getDescription() {
        return "Print 2D map using ascii art, this command only available while playing game";
    }

    @Override
    public void execute(Game game, List<String> args) {
        Map map = game.getCurrentLevel();
        map.printMap();
    }
}
