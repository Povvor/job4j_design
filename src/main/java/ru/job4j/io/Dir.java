package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.printf("Размер директории: %s%n", file.getTotalSpace());
        for (File subfile : file.listFiles()) {
            System.out.printf("%s Размер: %s Кб%n", subfile.getName(), subfile.length() / 1024);
        }
    }
}















