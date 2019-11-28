package com.zuhlke.paint.ascii;

import com.zuhlke.paint.ascii.figure.Renderable;
import com.zuhlke.paint.ascii.figure.ShapeStrategyFactoryImpl;
import com.zuhlke.paint.ascii.figure.ShapeStrategyFactory;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * The {@code ApplicationContextImpl} class represents an implementation of {@code ApplicationContext}.
 * It holds application state, as e.g. the current {@code Renderable}.
 * It reacts to updates issued by commands through rendering to the given {@code updateAction}.
 */
public class ApplicationContextImpl implements ApplicationContext {

    private AtomicBoolean executing;
    private Renderable currentRenderable;
    private ShapeStrategyFactory shapeStrategyFactory;
    private Consumer<? super String> updateAction;


    private ApplicationContextImpl(Consumer<? super String> updateAction) {
        executing = new AtomicBoolean();
        shapeStrategyFactory = ShapeStrategyFactoryImpl.create();
        this.updateAction = updateAction;
    }

    @Override
    public boolean isExecuting() {
        return executing.get();
    }

    @Override
    public void setExecuting(boolean executing) {
        this.executing.set(executing);
    }

    @Override
    public Renderable getCurrentRenderable() {
        return currentRenderable;
    }

    @Override
    public void setCurrentRenderable(Renderable currentRenderable) {
        this.currentRenderable = currentRenderable;
    }

    @Override
    public ShapeStrategyFactory getShapeStrategyFactory() {
        return shapeStrategyFactory;
    }

    /**
     * Upon update, render using {@code updateAction}.
     *
     */
    @Override
    public void update() {
        currentRenderable.render(updateAction);
    }

    public static ApplicationContext create(Consumer<? super String> updateAction) {
        return new ApplicationContextImpl(updateAction);
    }



}
