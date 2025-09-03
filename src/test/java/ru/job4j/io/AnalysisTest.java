package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void analysisTest(@TempDir Path tempDir) throws IOException {
        File source1 = tempDir.resolve("source1.txt").toFile();
        try (PrintWriter writer = new PrintWriter(source1)) {
            writer.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    300 10:59:01
                    500 11:01:02
                    200 11:02:02
                    200 11:03:01
                    500 11:04:01
                    400 11:05:01
                    500 11:06:01
                    400 11:07:02
                    300 11:08:02
                    """);
        }
        File output1 = tempDir.resolve("output1.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source1.getAbsolutePath(), output1.getAbsolutePath());
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(output1))) {
            reader.lines().forEach(s -> builder.append(s).append("\n"));
        }
        String result = builder.toString();
        String expected = """
                10:57:01;10:59:01;
                11:01:02;11:02:02;
                11:04:01;11:08:02;
                """;
        assertThat(result).isEqualTo(expected);
    }

}