package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.ApplicationContextImpl;
import com.zuhlke.paint.ascii.AsciiPaintException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTests {

    private CommandFactory commandFactory;
    private StringBuilder commandTestOutput;

    private void commandTestOutputAction(String s) {
        commandTestOutput.append(s);
    }

    @BeforeEach
    public void beforeEachTest() {
        commandTestOutput = new StringBuilder();
        ApplicationContext applicationContext = ApplicationContextImpl.create(this::commandTestOutputAction);
        commandFactory = CommandFactoryImpl.create(applicationContext);
    }

    @Test
    void create() {
        assertNotNull(commandFactory);
    }

    @Test
    void createCommandWithNullArgumentList() {
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(null));
    }

    @Test
    void createCommandWithEmptyArgumentList() {
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(new ArrayList<String>()));
    }

    @Test
    void createCanvasCommand() throws AsciiPaintException {
        List<String> command = Stream.of("C", "10", "10").collect(Collectors.toList());

        Command canvasCommand = commandFactory.createCommand(command);
        assertTrue(canvasCommand instanceof CanvasCommand);
    }

    @Test
    void createFillCommand() throws AsciiPaintException {
        List<String> command = Stream.of("B", "10", "10", "c").collect(Collectors.toList());

        Command fillCommand = commandFactory.createCommand(command);
        assertTrue(fillCommand instanceof FillCommand);
    }

    @Test
    void createQuitCommand() throws AsciiPaintException {
        List<String> command = Stream.of("Q").collect(Collectors.toList());

        Command quitCommand = commandFactory.createCommand(command);
        assertTrue(quitCommand instanceof QuitCommand);
    }

    private void assertShapeCommand(List<String> command) throws AsciiPaintException {
        Command shapeCommand = commandFactory.createCommand(command);
        assertTrue(shapeCommand instanceof ShapeCommand);
    }

    @Test
    void createRectangleCommand() throws AsciiPaintException {
        List<String> command = Stream.of("R", "1", "2", "5", "6").collect(Collectors.toList());
        assertShapeCommand(command);
    }

    @Test
    void createLineCommand() throws AsciiPaintException {
        List<String> command = Stream.of("L", "1", "2", "5", "6").collect(Collectors.toList());
        assertShapeCommand(command);
    }

    @Test
    void createUnknownCommand() {
        List<String> command = Stream.of("U").collect(Collectors.toList());
        assertThrows(AsciiPaintException.class, () -> commandFactory.createCommand(command));
    }

}
