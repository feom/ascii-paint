package com.zuhlke.paint.ascii;

@FunctionalInterface
public interface CreateFunction<U, V, R> {

    R create(U argument, V anotherArgument) throws AsciiPaintException;

}
