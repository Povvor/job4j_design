package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "weapon")
public class Weapon {
    private String name;
    private int damage;

    public Weapon() { }

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Weapon{"
                + "name='" + name + '\''
                + ", damage=" + damage
                + '}';
    }
}