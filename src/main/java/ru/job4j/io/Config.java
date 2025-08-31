package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> list = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            list = input.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String string : list) {
            validateAndPut(string);
        }

    }

    public String value(String key) {
        return values.get(key);
    }

    private void validateAndPut(String string) {
        string = string.trim();
        if (string.isEmpty() || string.startsWith("#")) {
            return;
        }
        int firstEqualSign = string.indexOf('=');
        if (firstEqualSign == -1) {
            throw new IllegalArgumentException();
        }
        String key = string.substring(0, firstEqualSign).trim();
        String value = string.substring(firstEqualSign + 1).trim();
        if (key.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        values.put(key, value);

    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
