package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private static Path path;
    private static String fileExtension;

    public static void main(String[] args) throws IOException {
        parseArgs(args);
        Path start = path;
        search(start, path -> path.toFile().getName().endsWith(fileExtension)).forEach(System.out::println);
    }

    private static void parseArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of arguments. Number must be 2.");
        }
        path = Paths.get(args[0]);
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Path does not exist.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Extension must start with '.'");
        }
        fileExtension = args[1];

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}