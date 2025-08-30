package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Multiply {

    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/multiply.txt")) {
            for (int i = 1; i <= 9; i++) {
                String out = "1 * " + i + " = " + i;
                output.write(out.getBytes());
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
