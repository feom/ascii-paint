package com.zuhlke.paint.ascii;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

/**
 * Start the application and use parmeterized tests to issue valid and invalid commands.
 * Reassigns the standard input stream to feed commands.
 */
class ApplicationTests {

    private static String ls;

    @BeforeAll
    public static void beforeAllTests() {
        ls = System.lineSeparator();
    }

    @ParameterizedTest
    @MethodSource("validCommands")
    void validInputCommands(String commands) {
        startApplication(commands);
    }

    static Stream<String> validCommands() {
        String drawCmds = "C 10 10" + ls + "L 1 2 1 3" + ls + "R 5 5 7 7" + ls + "B 8 8 c" + ls + "Q" + ls;
        String fillCmd = "C 10 10" + ls + "B 2 2 d" + ls + "B 3 3 d" + ls + "Q" + ls;
        return Stream.of(drawCmds, fillCmd);
    }

    @ParameterizedTest
    @MethodSource("invalidCommand")
    void invalidInputCommand(String commands) {
        startApplication(commands);
    }

    static Stream<String> invalidCommand() {
        String command = "Z 1 2" + ls + "Q" + ls;
        return Stream.of(command);
    }

    private void startApplication(String commands) {
        final InputStream in = System.in;
        final InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        System.setIn(inputStream);
        String [] args = new String[] {};
        Application.main(args);
        System.setIn(in);
    }


}