package io.muic.ssc.zork.Item;

import io.muic.ssc.zork.Item.potion.MegaPotion;
import io.muic.ssc.zork.Item.potion.SmallPotion;
import io.muic.ssc.zork.Item.potion.MedPotion;
import io.muic.ssc.zork.Item.potion.Potion;
import io.muic.ssc.zork.Item.weapon.Rifle;
import io.muic.ssc.zork.Item.weapon.Sword;
import io.muic.ssc.zork.Item.weapon.ShotGun;
import io.muic.ssc.zork.Item.weapon.Weapon;

import java.util.Locale;

public class ItemFactory {

    public Weapon createWeapon(String input) {
        String weapon = input.trim().toLowerCase(Locale.ROOT);
        if (weapon.equals("sword")) {
            return new Sword();
        }
        else if (weapon.equals("rifle")) {
            return new Rifle();
        }
        else if (weapon.equals("shot gun")) {
            return new ShotGun();
        }
        return null;
    }

    public Potion createPotion(String input) {
        String potion = input.toLowerCase(Locale.ROOT);
        if (potion.equals("mega potion")) {
            return new MegaPotion();
        }
        else if (potion.equals("small potion")) {
            return new SmallPotion();
        }
        else if (potion.equals("medium potion")) {
            return new MedPotion();
        }
        return null;
    }
}
