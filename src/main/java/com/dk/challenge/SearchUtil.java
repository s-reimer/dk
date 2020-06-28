package com.dk.challenge;

import java.util.ArrayList;
import java.util.List;

public final class SearchUtil {

    /**
     * Searches the data between indexBegin and indexEnd for a range of length winLength where all values are above the
     * threshold.
     *
     * @return the first index where this condition is met
     */
    public int searchContinuityAboveValue(List<Double> data, int indexBegin, int indexEnd, double threshold, int winLength)
            throws InvalidInputExcpeption {
        validateInput(data, indexBegin, indexEnd, threshold, Integer.MAX_VALUE, winLength);
        int startPosition = indexBegin;
        int lastPossibleStartPosition = indexEnd - winLength + 1;

        while (startPosition <= lastPossibleStartPosition) {
            boolean validRange = true;
            for (int i = startPosition; i < startPosition + winLength; i++) {
                if (!isValidValue(data.get(i), threshold, Integer.MAX_VALUE)) {
                    startPosition = i + 1;
                    validRange = false;
                    break;
                }
            }
            if (validRange) {
                return startPosition;
            }
        }

        return -1;
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

        int startPosition = indexBegin;
        int lastPossibleStartPosition = indexEnd + winLength - 1;

        while (startPosition >= lastPossibleStartPosition) {
            boolean validRange = true;
            for (int i = startPosition; i > startPosition - winLength; i--) {
                if (!isValidValue(data.get(i), thresholdLo, thresholdHi)) {
                    startPosition = i - 1;
                    validRange = false;
                    break;
                }
            }
            if (validRange) {
                return startPosition;
            }
        }

        return -1;
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

        int startPosition = indexBegin;
        int lastPossibleStartPosition = indexEnd - winLength + 1;

        while (startPosition <= lastPossibleStartPosition) {
            boolean validRange = true;
            for (int i = startPosition; i < startPosition + winLength; i++) {
                if (data1.get(i) <= threshold1 || data2.get(i) <= threshold2) {
                    startPosition = i + 1;
                    validRange = false;
                    break;
                }
            }
            if (validRange) {
                return startPosition;
            }
        }

        return -1;
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


        List<Range> validRanges = new ArrayList<>();
        boolean isPreviousRangeValid = false;
        for (int i = indexBegin; i <= indexEnd - winLength + 1; i++) {
            if (isPreviousRangeValid) {
                // If the previous range is valid, we only need to check the next element to see if it's valid, no
                // need to check any values that were part of the previous range
                int endRangeIndex = i + winLength - 1;
                Double nextValue = data.get(endRangeIndex);
                if (isValidValue(nextValue, thresholdLo, thresholdHi)) {
                    validRanges.add(new Range(i, endRangeIndex));
                } else {
                    isPreviousRangeValid = false;
                }
            } else {
                boolean isValidStartIndex = true;
                for (int j = i; j < i + winLength; j++) {
                    if (!isValidValue(data.get(j), thresholdLo, thresholdHi)) {
                        isValidStartIndex = false;
                        break;
                    }
                }
                if (isValidStartIndex) {
                    validRanges.add(new Range(i, i + winLength - 1));
                    isPreviousRangeValid = true;
                }
            }
        }
        return validRanges;
    }

    private boolean isValidValue(double value, double thresholdLo, double thresholdHi) {
        return value > thresholdLo && value < thresholdHi;
    }

    private void validateInput(List<Double> data, int lowIndex, int highIndex, double thresholdLo, double thresholdHi,
                               int winLength) throws InvalidInputExcpeption {
        if (lowIndex < 0 || lowIndex + winLength > highIndex + 1 || data.size() < lowIndex + winLength ||
                data.size() <= highIndex || thresholdLo > thresholdHi) {
            throw new InvalidInputExcpeption();
        }
    }
}
