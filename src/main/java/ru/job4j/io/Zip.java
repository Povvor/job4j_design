package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final HashMap<String, String> values = new HashMap<>();
    private Path directory;
    private String exclude;
    private File output;

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getName()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                    zip.closeEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Error: Missing required arguments");
        }
        for (String arg : args) {
            validateArg(arg);
        }
        if (values.size() != 3) {
            throw new IllegalArgumentException("Error: Missing required arguments");
        }

    }

    private void validateArg(String string) {
        if (!string.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument 'request=?msg=Exit=' does not start with a '-' character");
        }
        int firstEqualSign = string.indexOf('=');
        if (firstEqualSign == -1) {
            throw new IllegalArgumentException("Error: This argument '" + string + "' does not contain an equal sign");
        }
        String key = string.substring(1, firstEqualSign).trim();
        String value = string.substring(firstEqualSign + 1).trim();
        if (key.isEmpty() || key.charAt(0) == '-') {
            throw new IllegalArgumentException("Error: This argument '-=" + value + "' does not contain a key");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '-" + key + "=' does not contain a value");
        }
        values.put(key, value);
    }

    private void parseArgs() {
        for (String key : values.keySet()) {
           switch (key) {
               case "d":
                   directory = Paths.get(values.get(key));
                   if (!directory.toFile().exists()) {
                       throw new IllegalArgumentException("Error: directory '" + values.get(key) + "' does not exist");
                   }
                   break;
               case "e":
                   exclude = values.get(key);
                   if (!exclude.startsWith(".")) {
                       throw new IllegalArgumentException("Error: Exclude value '" + values.get(key) + "' does not correct");
                   }
                   break;
               case "o":
                   output = new File(values.get(key));
                   if (!output.getName().endsWith(".zip")) {
                       throw new IllegalArgumentException("Error: Output directory '" + values.get(key) + "' does not end with .zip");
               }
                   break;
               default:
                   throw new IllegalArgumentException("Error: Unknown argument '" + key + "'");
           }
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validateArgs(args);
        zip.parseArgs();
        zip.packFiles(Search.search(zip.directory, path -> !path.toString().endsWith(zip.exclude)), zip.output);
    }
}