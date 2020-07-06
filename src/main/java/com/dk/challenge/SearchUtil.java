package com.dk.challenge;

import java.util.ArrayList;
import java.util.List;

public final class SearchUtil {

    private static final int FORWARD_LOOP_INCREMENT = 1;
    private static final int BACKWARD_LOOP_INCREMENT = -1;

    /**
     * Searches the data between indexBegin and indexEnd for a range of length winLength where all values are above the
     * threshold.
     *
     * @return the first index where this condition is met
     */
    public int searchContinuityAboveValue(List<Double> data, int indexBegin, int indexEnd, double threshold, int winLength)
            throws InvalidInputExcpeption {
        validateInput(data, indexBegin, indexEnd, threshold, Integer.MAX_VALUE, winLength);
        List<Range> ranges = searchHelper(
                indexBegin,
                FORWARD_LOOP_INCREMENT,
                winLength,
                index -> isValueInThresholds(data.get(index), threshold, Integer.MAX_VALUE),
                (results, currentIndex) -> results.size() != 1 && (currentIndex <= indexEnd));
        return getFirstIndexFromRanges(ranges);
    }

    /**
     * Searches the data backwards between indexBegin and indexEnd for a range of length winLength where all values are
     * above thresholdLo and less than thresholdHi.
     *
     * @return the first index where this condition is met
     */
    public int backSearchContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd, double thresholdLo,
                                               double thresholdHi, int winLength) throws InvalidInputExcpeption {
        validateInput(data, indexEnd, indexBegin, thresholdLo, thresholdHi, winLength);
        List<Range> ranges = searchHelper(
                indexBegin,
                BACKWARD_LOOP_INCREMENT,
                winLength,
                index -> isValueInThresholds(data.get(index), thresholdLo, thresholdHi),
                (results, currentIndex) -> results.size() != 1 && (currentIndex >= indexEnd));
        return getFirstIndexFromRanges(ranges);
    }

    /**
     * Searches both data1 and data2 between indexBegin and indexEnd for a range of length winLength where all values
     * in data1 are above the threshold1 and all values in data2 are above the threshold2.
     *
     * @return the first index where this condition is met
     */
    public int searchContinuityAboveValueTwoSignals(List<Double> data1, List<Double> data2, int indexBegin, int indexEnd,
                                                    double threshold1, double threshold2, int winLength) throws InvalidInputExcpeption {
        validateInput(data1, indexBegin, indexEnd, threshold1, Integer.MAX_VALUE, winLength);
        validateInput(data2, indexBegin, indexEnd, threshold2, Integer.MAX_VALUE, winLength);

        List<Range> ranges = searchHelper(
                indexBegin,
                FORWARD_LOOP_INCREMENT,
                winLength,
                index -> (
                        isValueInThresholds(data1.get(index), threshold1, Integer.MAX_VALUE) &&
                                isValueInThresholds(data2.get(index), threshold2, Integer.MAX_VALUE)),
                (results, currentIndex) -> results.size() != 1 && (currentIndex <= indexEnd));
        return getFirstIndexFromRanges(ranges);
    }

    /**
     * Searches the data  between indexBegin and indexEnd for a range of length winLength where all values are above
     * thresholdLo and less than thresholdHi.
     *
     * @return all index Ranges where this condition is met
     */
    public List<Range> searchMultiContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd, double thresholdLo,
                                                        double thresholdHi, int winLength) throws InvalidInputExcpeption {
        validateInput(data, indexBegin, indexEnd, thresholdLo, thresholdHi, winLength);
        return searchHelper(
                indexBegin,
                FORWARD_LOOP_INCREMENT,
                winLength,
                index -> isValueInThresholds(data.get(index), thresholdLo, thresholdHi),
                (results, currentIndex) -> (currentIndex <= indexEnd));
    }

    private List<Range> searchHelper(int indexBegin, int loopIncrement, int winLength,
                                     IsIndexValidFunction validIndexFunction,
                                     KeepSearchingFunction keepSearchingFunction) {
        List<Range> results = new ArrayList<>();
        List<Integer> currentStreak = new ArrayList<>();

        for (int i = indexBegin; keepSearchingFunction.apply(results, i); i += loopIncrement) {
            if (validIndexFunction.apply(i)) {
                currentStreak.add(i);
            } else {
                if (currentStreak.size() >= winLength) {
                    results.add(new Range(currentStreak.get(0), currentStreak.get(currentStreak.size() - 1)));
                }
                currentStreak = new ArrayList<>();
            }
        }

        if (currentStreak.size() >= winLength) {
            results.add(new Range(currentStreak.get(0), currentStreak.get(currentStreak.size() - 1)));
        }
        return results;
    }

    private boolean isValueInThresholds(double value, double thresholdLo, double thresholdHi) {
        return value > thresholdLo && value < thresholdHi;
    }

    private void validateInput(List<Double> data, int lowIndex, int highIndex, double thresholdLo, double thresholdHi,
                               int winLength) throws InvalidInputExcpeption {
        if (lowIndex < 0 || lowIndex + winLength > highIndex + 1 || data.size() < lowIndex + winLength ||
                data.size() <= highIndex || thresholdLo > thresholdHi) {
            throw new InvalidInputExcpeption();
        }
    }

    private int getFirstIndexFromRanges(List<Range> ranges) {
        return ranges.size() < 1 ? -1 : ranges.get(0).getBeginIndex();
    }
}
