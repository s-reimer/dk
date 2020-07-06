package com.dk.challenge;

import java.util.List;

@FunctionalInterface
public interface KeepSearchingFunction {

    /**
     * Returns true if the search algorithm should continue looking for results, given the list of exisiting results and
     * the current index being checked.
     */
    boolean apply(List<Range> results, int currentIndex);
}