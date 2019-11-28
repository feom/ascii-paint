package com.zuhlke.paint.ascii;

import com.zuhlke.paint.ascii.figure.Canvas;
import com.zuhlke.paint.ascii.figure.Renderable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextTests {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void beforeEachTest() {
        applicationContext = ApplicationContextImpl.create(System.out::println);
        Renderable renderable = Canvas.create(10, 10);
        applicationContext.setCurrentRenderable(renderable);
    }

    @Test
    void create() {
        assertNotNull(applicationContext);
    }

    @Test
    void isExecuting() {
        applicationContext.setExecuting(false);
        assertFalse(applicationContext.isExecuting());
        applicationContext.setExecuting(true);
        assertTrue(applicationContext.isExecuting());
    }

    @Test
    void currentRenderable() {
        Renderable someRenderable = Canvas.create(5, 5);
        applicationContext.setCurrentRenderable(someRenderable);
        assertTrue(applicationContext.getCurrentRenderable() == someRenderable);
    }

    @Test
    void getShapeStrategyFactory() {
        assertNotNull(applicationContext.getShapeStrategyFactory());
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> applicationContext.update());
    }

}