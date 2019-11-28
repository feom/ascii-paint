package com.zuhlke.paint.ascii;

import com.zuhlke.paint.ascii.figure.Renderable;
import com.zuhlke.paint.ascii.figure.ShapeStrategyFactory;

/**
 * An {@code ApplicationContext} holds application state and
 * allows to react to updates issued by commands.
 */
public interface ApplicationContext {

    /**
     * Indicates if application is executing.
     *
     * @return true if executing, false otherwise
     */
    boolean isExecuting();

    /**
     * Set if the application should continue executing.
     *
     * @param executing the execution state
     */
    void setExecuting(boolean executing);

    /**
     * Get the current {@code Renderable} used by the application.
     *
     * @return the current {@code Renderable}
     */
    Renderable getCurrentRenderable();

    /**
     * Set the current {@code Renderable} of the application.
     *
     * @param currentRenderable the {@code Renderable} to set
     */
    void setCurrentRenderable(Renderable currentRenderable);

    /**
     * Get the {@code ShapeStrategyFactory} of this context.
     *
     * @return the {@code ShapeStrategyFactory}
     */
    ShapeStrategyFactory getShapeStrategyFactory();

    /**
     * React to an update of application context state.
     *
     */
    void update();
}
