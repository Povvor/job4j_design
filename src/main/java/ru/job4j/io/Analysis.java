package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    public void unavailable(String source, String target) {
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            input = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String timeStart = null;
        String timeEnd = null;
        for (String string : input) {
            String[] split = string.split(" ");
            if ("400".equals(split[0]) || ("500".equals(split[0]))) {
                if (timeStart == null) {
                    timeStart = split[1];
                }
                timeEnd = split[1];
            } else if (timeEnd != null) {
                timeEnd = split[1];
                String entry  = timeStart
                        + ";"
                        + timeEnd
                        + ";";
                output.add(entry);
                timeStart = null;
                timeEnd = null;
            }
        }
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            output.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}