package ru.job4j.serialization.json;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        /* JSONObject из json-строки строки */
        JSONObject jsonWeapon = new JSONObject("{\"name\":\"+sword\",\"damage\":\"+10\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Stone skin");
        list.add("Shield");
        JSONArray jsonPerks = new JSONArray(list);

        Hero hero = new Hero("Cornitor", false, 30, new Weapon("Sword", 10),
                new String[] {"Stone skin", "Shield"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", hero.getName());
        jsonObject.put("mainHero", hero.isMainHero());
        jsonObject.put("lvl", hero.getLvl());
        jsonObject.put("weapon", jsonWeapon);
        jsonObject.put("perks", jsonPerks);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(hero));
    }
}