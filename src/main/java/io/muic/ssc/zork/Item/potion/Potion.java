package io.muic.ssc.zork.Item.potion;

import io.muic.ssc.zork.Item.Item;

public class Potion extends Item {

    public int getIncreaseHp() {
        return increasedHp;
    }

    public int increasedHp;

    public Potion(String name, String description, int increaseHp) {
        super(name, description);
        this.increasedHp = increaseHp;
    }
}
