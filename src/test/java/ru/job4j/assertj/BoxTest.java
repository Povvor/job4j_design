package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Sphere")
                .isNotBlank()
                .hasSize(6);
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Cube")
                .isNotBlank()
                .hasSize(4);
    }

    @Test
    void getNumberOfVerticesTestWhenCube() {
        Box box = new Box(8, 12);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8);
    }

    @Test
    void getNumberOfVerticesTestWhenSphere() {
        Box box = new Box(0, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void isExistWhenUnknown() {
        Box box = new Box(12, 30);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void isExistWhenImpossible() {
        Box box = new Box(-5, 30);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void getAreaOfSphere() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        double expected = 1256.636f;
        assertThat(result)
                .isEqualTo(expected, withPrecision(0.01))
                .isPositive();
    }

    @Test
    void getAreaOfTetrahedron() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        double expected = 173.205f;
        assertThat(result)
                .isEqualTo(expected, withPrecision(0.01))
                .isPositive();
    }
}