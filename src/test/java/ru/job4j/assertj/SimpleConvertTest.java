package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .allSatisfy(e -> assertThat(e).isNotBlank());
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> result = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(result)
                .hasSize(5)
                .contains("first", Index.atIndex(0))
                .contains("five", Index.atIndex(4))
                .contains("three")
                .doesNotContain("six")
                .doesNotContain("four", Index.atIndex(0))
                .startsWith("first")
                .endsWith("five")
                .containsExactly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("second", "first", "three", "four", "five")
                .allSatisfy(e -> assertThat(e).isNotBlank());
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> result = simpleConvert.toSet("first", "first", "second", "three", "four");
        assertThat(result)
                .hasSize(4)
                .contains("three")
                .doesNotContain("six")
                .containsExactlyInAnyOrder("first", "second", "three", "four")
                .allSatisfy(e -> assertThat(e).isNotBlank());
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> result = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(result)
                .hasSize(5)
                .containsEntry("first", 0)
                .doesNotContainKey("six")
                .doesNotContainValue(6);
    }
}
