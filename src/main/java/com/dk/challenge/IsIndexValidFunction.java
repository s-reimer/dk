package com.dk.challenge;

@FunctionalInterface
public interface IsIndexValidFunction {

    /**
     * Returns true if the value at index should be considered valid. Otherwise, returns false
     */
    boolean apply(int index);
}