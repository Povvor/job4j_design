package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int integer = 33;
        short shortNumber = 10;
        float floatNumber = 2.3f;
        double doubleNumber = 3.3d;
        long longNumber = 4L;
        byte byteNumber = 5;
        char character = 'a';
        boolean booleanValue = true;

        LOG.debug("Simple variables - int : {}, short : {}, float : {}, double : {}, long : {}, byte : {}, char : {}, boolean : {} ", integer, shortNumber, floatNumber, doubleNumber, longNumber, byteNumber, character, booleanValue);
    }
}