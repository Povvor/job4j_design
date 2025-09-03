package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, String> paths = new HashMap<>();
    private final Set<FileProperty> duplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (Files.isDirectory(file)) {
            return super.visitFile(file, attributes);
        }

        FileProperty property = new FileProperty(attributes.size(), file.getFileName().toString());

        if (!paths.containsKey(property)) {
            paths.put(property, file.toAbsolutePath().toString());
        } else if (!paths.get(property).isEmpty()) {
            System.out.println(paths.get(property));
            paths.put(property, "");
            System.out.println(file.toAbsolutePath());
        } else {
            System.out.println(file.toAbsolutePath());
        }

        return super.visitFile(file, attributes);

    }
}