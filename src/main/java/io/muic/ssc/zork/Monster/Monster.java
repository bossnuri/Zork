package io.muic.ssc.zork.Monster;

import io.muic.ssc.zork.GameOutput;

public class Monster {

    private String name;
    private String description;
    private int maxHp;
    private int hp;
    private int attackDmg;
    private double attackProbability;
    private boolean alive;

    public Monster(String name, String description, int hp, int attackDmg, double attackProbability) {
        this.name = name;
        this.description = description;
        this.maxHp = hp;
        this.hp = hp;
        this.attackDmg = attackDmg;
        this.attackProbability = attackProbability;
        alive = true;
    }

    public void decreaseHealth(int attackDmg) {
        hp -= attackDmg;
        if (hp <= 0) {
            hp = 0;
            alive = false;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public double getAttackProbability() {
        return attackProbability;
    }

    public boolean isAlive() {
        return alive;
    }

    public void printStats() {
        GameOutput output = new GameOutput();
        output.println("");
        output.println("[ Monster's Stats ]");
        output.println("   Name: " + name);
        output.println("   HP: " + hp + "/" + maxHp);
        output.println("   Description: " + description);
        output.println("");
    }
}

