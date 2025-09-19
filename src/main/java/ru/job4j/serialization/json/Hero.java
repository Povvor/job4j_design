package ru.job4j.serialization.json;

import java.util.Arrays;

public class Hero {
    private final String name;
    private final boolean mainHero;
    private final int lvl;
    private final Weapon weapon;
    private final String[] perks;

    public Hero(String name, boolean mainHero, int lvl, Weapon weapon, String[] perks) {
        this.name = name;
        this.mainHero = mainHero;
        this.lvl = lvl;
        this.weapon = weapon;
        this.perks = perks;
    }

    @Override
    public String toString() {
        return "Hero{"
                + "name=" + name
                + " mainHero=" + mainHero
                + ", lvl=" + lvl
                + ", weapon=" + weapon
                + ", perks=" + Arrays.toString(perks)
                + '}';
    }
}