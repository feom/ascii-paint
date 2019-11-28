package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.AsciiPaintException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTests {

    private Validator validator;

    @BeforeEach
    public void beforeEachTest() {
        validator = Validator.create();
    }

    @Test
    void create() {
        assertNotNull(validator);
    }

    @Test
    void allStrictlyPositive() throws AsciiPaintException {
        validator.validateStrictlyPositive(1, 2);
    }

    @Test
    void oneNegative() {
        assertThrows(AsciiPaintException.class, () -> validator.validateStrictlyPositive(1, -2));
    }

    @Test
    void allNegative() {
        assertThrows(AsciiPaintException.class, () -> validator.validateStrictlyPositive(-1, -2));
    }

    @Test
    void validCoordinatePairs() throws AsciiPaintException {
        validator.validateCoordinatePairs(1, 2, 4, 5);
    }

    @Test
    void firstBiggerCoordinates() {
        assertThrows(AsciiPaintException.class, () -> validator.validateCoordinatePairs(4, 5, 1, 2));
        assertThrows(AsciiPaintException.class, () -> validator.validateCoordinatePairs(1, 5, 1, 2));
    }

    @Test
    void validNumberOfArguments() throws AsciiPaintException {
        List<String> arguments = new ArrayList<>();
        arguments.add("Q");
        validator.validateNumberOfArguments(arguments, 1);
    }

    @Test
    void emptyNumberOfArguments() {
        List<String> arguments = new ArrayList<>();
        assertThrows(AsciiPaintException.class, () -> validator.validateNumberOfArguments(arguments, 1));
    }

    @Test
    void nullNumberOfArguments() {
        assertThrows(AsciiPaintException.class, () -> validator.validateNumberOfArguments(null, 1));
    }
}