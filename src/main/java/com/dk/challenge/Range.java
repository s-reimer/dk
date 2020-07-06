package com.dk.challenge;

import java.util.Objects;

public class Range {
    private final int beginIndex;
    private final int endIndex;

    public Range(int beginIndex, int endIndex) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    @Override
    public String toString() {
        return beginIndex + "-" + endIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return beginIndex == range.beginIndex &&
                endIndex == range.endIndex;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginIndex, endIndex);
    }
}
