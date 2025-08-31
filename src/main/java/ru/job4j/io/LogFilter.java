package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            temp = input.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String string : temp) {
            String[] split = string.split(" ");
            if ("404".equals(split[split.length - 2])) {
                result.add(string);
            }
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            data.forEach(output::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
