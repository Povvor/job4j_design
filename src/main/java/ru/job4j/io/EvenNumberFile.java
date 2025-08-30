package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        String result = "";
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            result = text.toString();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        String[] split = result.split(System.lineSeparator());
        for (String s : split) {
            System.out.println(s + " " + (Integer.parseInt(s) % 2 == 0));
        }

    }
}