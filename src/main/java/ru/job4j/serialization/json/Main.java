package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Hero person = new Hero("Cornitor", false, 30, new Weapon("Sword", 10),
                new String[] {"Stone skin", "Shield"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Создаём новую json-строку с модифицированными данными*/
        final String personJson =
                "{"
                        + "\"name\":\"Cornitor\","
                        + "\"mainHero\":false,"
                        + "\"lvl\":8,"
                        + "\"Weapon\":"
                        + "{"
                        + "\"Sword\":\"10\""
                        + "},"
                        + "\"perks\":"
                        + "[\"Stone Skin\",\"Shield\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final Hero personMod = gson.fromJson(personJson, Hero.class);
        System.out.println(personMod);
    }
}