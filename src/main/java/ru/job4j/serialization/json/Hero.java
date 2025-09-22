package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Arrays;

@XmlRootElement(name = "hero")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hero {
    private String name;
    private boolean mainHero;
    private int lvl;
    private Weapon weapon;
    private String[] perks;

    public Hero() { }

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

    public String getName() {
        return name;
    }

    public boolean isMainHero() {
        return mainHero;
    }

    public int getLvl() {
        return lvl;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String[] getPerks() {
        return perks;
    }
}