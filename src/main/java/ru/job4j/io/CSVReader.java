package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private Path file;
    private String delimiter;
    private File output;
    private String[] filterValues;

    public static void handle(ArgsName argsName) throws Exception {
        CSVReader csvReader = new CSVReader();
        csvReader.parseArgs(argsName);

        List<String> inputStrings = csvReader.readFile(csvReader.file);
        Set<Integer> filteredIndexes = csvReader.defineFilteredIndexes(inputStrings);

        StringBuilder builder = new StringBuilder();
        for (String inputString : inputStrings) {
            String[] split = inputString.split(csvReader.delimiter);
            for (int index : filteredIndexes) {
                builder.append(split[index]).append(csvReader.delimiter);
            }
            if (!builder.isEmpty()) {
                builder.deleteCharAt(builder.length() - 1);
            }
            builder.append(System.lineSeparator());
        }

        if (csvReader.output != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvReader.output))) {
                writer.write(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(builder);
        }
    }

    private void parseArgs(ArgsName argsName) throws Exception {
        if (argsName.getValues().size() != 4) {
            throw new Exception("Same argument appears two times");
        }
        Map<String, String> values = argsName.getValues();
        for (String key : argsName.getValues().keySet()) {
            switch (key) {
                case "path":
                    file = Paths.get(values.get(key));
                    if (!file.toFile().exists()) {
                        throw new IllegalArgumentException("Error: directory '" + values.get(key) + "' does not exist");
                    }
                    break;
                case "delimiter":
                    delimiter = values.get(key);
                    break;
                case "out":
                    output = "stdout".equals(values.get(key)) ? null : new File(values.get(key));
                    break;
                case "filter":
                    filterValues = values.get(key).split(",");
                    break;
                default:
                    throw new IllegalArgumentException("Error: Unknown argument '" + key + "'");
            }
        }
    }

    private List<String> readFile(Path file) {
        List<String> strings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(file.toFile())).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                strings.add(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private Set<Integer> defineFilteredIndexes(List<String> inputStrings) {
        Set<Integer> output = new LinkedHashSet<>();
        String[] split = inputStrings.get(0).split(delimiter);
        for (String filterValue : filterValues) {
            for (int j = 0; j < split.length; j++) {
                if (filterValue.equals(split[j])) {
                    output.add(j);
                }
            }
        }
        return output;
    }

    public static void main(String[] args) throws Exception {
       if (args.length != 4) {
           throw new IllegalArgumentException("Error: Wrong number of arguments");
       }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}