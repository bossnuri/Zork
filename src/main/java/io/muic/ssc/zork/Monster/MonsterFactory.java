package io.muic.ssc.zork.Monster;

import java.util.Locale;

public class MonsterFactory {
    public Monster createMonster(String input) {
        String monster = input.toLowerCase(Locale.ROOT);
        if (monster.equals("monkey king")) {
            return new MonkeyKing();
        }
        else if (monster.equals("earth shaker")) {
            return new EarthShaker();
        }
        else if (monster.equals("queen of pain")) {
            return new QueenOfPain();
        }
        else if (monster.equals("medusa")) {
            return new Medusa();
        }
        return null;
    }
}
