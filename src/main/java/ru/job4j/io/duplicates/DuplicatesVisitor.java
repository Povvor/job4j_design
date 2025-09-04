package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<String>> paths = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (Files.isDirectory(file)) {
            return super.visitFile(file, attributes);
        }

        FileProperty property = new FileProperty(attributes.size(), file.getFileName().toString());

        paths.putIfAbsent(property, new ArrayList<>());
        paths.get(property).add(file.toString());
        return super.visitFile(file, attributes);
    }

    public void printDublicates() {
        for (List<String> list : paths.values()) {
            if (list.size() > 1) {
                list.forEach(System.out::println);
            }
        }
    }
}