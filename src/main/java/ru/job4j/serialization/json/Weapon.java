package ru.job4j.serialization.json;

public class Weapon {
    private final String name;
    private final int damage;

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