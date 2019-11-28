package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.ApplicationContextImpl;
import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.figure.Canvas;
import com.zuhlke.paint.ascii.figure.Renderable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests commands using command combinations as parameterized test input.
 *
 */
class CommandTests {

    private ApplicationContext applicationContext;
    private StringBuilder commandTestOutput;
    private CommandFactory commandFactory;

    private void commandTestOutputAction(String s) {
        commandTestOutput.append(s);
    }

    @BeforeEach
    void beforeEachTest() {
        commandTestOutput = new StringBuilder();
        applicationContext = ApplicationContextImpl.create(this::commandTestOutputAction);
        Renderable renderable = Canvas.create(10, 10);
        applicationContext.setCurrentRenderable(renderable);
        commandFactory = CommandFactoryImpl.create(applicationContext);
    }

    private List<String> splitUp(String command) {
        return Arrays.asList(command.split("\\s"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"C 10 10", "L 1 1 5 6", "R 1 1 4 5", "B 9 9 c", "Q"})
    void executeValidCommand(String command) throws AsciiPaintException {
        commandFactory.createCommand(splitUp(command)).execute();
        if (command.startsWith("Q")) {
            assertTrue(commandTestOutput.toString().isEmpty());
        } else {
            assertFalse(commandTestOutput.toString().isEmpty());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"C 10", "L 1 1 5", "R 1 1 4", "B 9 9"})
    void invalidCommandLength(String command) {
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(splitUp(command)));
    }

    @Test
    void nullOrEmptyCommandLength() {
        List<String> command = Stream.of("").collect(Collectors.toList());
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(command));
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"L 1 1 5 4", "R 1 1 4 2", "B 9 9 c"})
    void noCanvasAvailable(String command) {
        applicationContext.setCurrentRenderable(null);
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(splitUp(command)).validate());
    }

    @ParameterizedTest
    @ValueSource(strings = {"C a 10", "L 1 r 5 4", "R 1 1 w 2", "B t 9 c"})
    void nonNumericInput(String command) {
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(splitUp(command)).validate());
    }

    @ParameterizedTest
    @ValueSource(strings = {"B 9 9 ab"})
    void unknownFillColour(String command) {
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(splitUp(command)).validate());
    }

}