package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/test/testWithCommentsAndBlanks.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Password")).isEqualTo("123");
        assertThat(config.value("Login")).isEqualTo("Alex");
    }

    @Test
    void whenPairWithErrorOne() {
        String path = "data/test/testWithError1.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithErrorTwo() {
        String path = "data/test/testWithError2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithErrorThree() {
        String path = "data/test/testWithError3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithErrorFour() {
        String path = "data/test/testWithError4.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTwoEqualSigns() {
        String path = "data/test/testWithTwoEqualsSigns.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Password")).isEqualTo("123=23=");
    }
}