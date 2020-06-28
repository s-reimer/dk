package com.dk.challenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestSearchUtil {

    private final SearchUtil searchUtil = new SearchUtil();

    @Test
    public void testSearchContinuityAboveValue_allMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        int index = searchUtil.searchContinuityAboveValue(data, 0, 3, 1.5, 2);
        assertEquals(0, index);
    }

    @Test
    public void testSearchContinuityAboveValue_someMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 1.0, 2.0, 2.0, 3.0, 4.0, 5.0);
        int index = searchUtil.searchContinuityAboveValue(data, 0, 6, 1.5, 3);
        assertEquals(2, index);
    }

    @Test
    public void testSearchContinuityAboveValue_noMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        int index = searchUtil.searchContinuityAboveValue(data, 0, 3, 5, 1);
        assertEquals(-1, index);
    }

    @Test
    public void testSearchContinuityAboveValue_invalidInput() {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        try {
            searchUtil.searchContinuityAboveValue(data, 0, 7, 1.5, 2);
            fail("No exception thrown");
        } catch (InvalidInputExcpeption e) {
        }
    }

    @Test
    public void testBackSearchContinuityAboveValue_allMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        int index = searchUtil.backSearchContinuityWithinRange(data, 2, 0, 1.5, 4, 3);
        assertEquals(2, index);
    }

    @Test
    public void testBackSearchContinuityAboveValue_someMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 1.0, 2.0, 2.0, 3.0, 4.0, 5.0);
        int index = searchUtil.backSearchContinuityWithinRange(data, 6, 1, 1.5, 4.5, 2);
        assertEquals(5, index);
    }

    @Test
    public void testBackSearchContinuityAboveValue_noMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        int index = searchUtil.backSearchContinuityWithinRange(data, 3, 0, 1.5, 1.6, 3);
        assertEquals(-1, index);
    }

    @Test
    public void testBackSearchContinuityAboveValue_invalidInput() {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        try {
            searchUtil.backSearchContinuityWithinRange(data, 3, 0, 7, 1, 3);
            fail("No exception thrown");
        } catch (InvalidInputExcpeption e) {
        }
    }

    @Test
    public void testSearchContinuityAboveValueTwoSignals_allMatch() throws InvalidInputExcpeption {
        List<Double> data1 = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        List<Double> data2 = data1;
        int index = searchUtil.searchContinuityAboveValueTwoSignals(data1, data2, 0, 2, 1.5, 1.5, 3);
        assertEquals(0, index);
    }

    @Test
    public void testSearchContinuityAboveValueTwoSignals_someMatch() throws InvalidInputExcpeption {
        List<Double> data1 = Arrays.asList(2.0, 1.0, 2.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> data2 = Arrays.asList(5.0, 6.0, 7.0, 5.0, 4.0, 3.0, 2.0);
        int index = searchUtil.searchContinuityAboveValueTwoSignals(data1, data2, 0, 6, 1.5, 3.0, 3);
        assertEquals(2, index);
    }

    @Test
    public void testSearchContinuityAboveValueTwoSignals_oneColumnMatch() throws InvalidInputExcpeption {
        List<Double> data1 = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        List<Double> data2 = data1;

        int index = searchUtil.searchContinuityAboveValueTwoSignals(data1, data2, 0, 2, 1.5, 4.0, 2);
        assertEquals(-1, index);
    }

    @Test
    public void testSearchContinuityAboveValueTwoSignals_noMatch() throws InvalidInputExcpeption {
        List<Double> data1 = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        List<Double> data2 = data1;

        int index = searchUtil.searchContinuityAboveValueTwoSignals(data1, data2, 0, 2, 4.0, 4.0, 2);
        assertEquals(-1, index);
    }

    @Test
    public void testSearchContinuityAboveValueTwoSignals_invalidInput() {
        List<Double> data1 = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        List<Double> data2 = data1;
        try {
            searchUtil.searchContinuityAboveValueTwoSignals(data1, data2, 0, 2, 4.0, 4.0, 4);
            fail("No exception thrown");
        } catch (InvalidInputExcpeption e) {
        }
    }

    @Test
    public void testSearchMultiContinuityWithinRange_oneMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        List<Range> actual = searchUtil.searchMultiContinuityWithinRange(data, 0, 3, 0, 4, 4);

        List<Range> expected = Collections.singletonList(new Range(0, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMultiContinuityWithinRange_overlappingMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        List<Range> actual = searchUtil.searchMultiContinuityWithinRange(data, 0, 3, 0, 4, 2);

        List<Range> expected = Arrays.asList(new Range(0, 1), new Range(1, 2), new Range(2, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMultiContinuityWithinRange_distinctMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 1.0, 2.0, 2.0);
        List<Range> actual = searchUtil.searchMultiContinuityWithinRange(data, 0, 4, 1.5, 4, 2);

        List<Range> expected = Arrays.asList(new Range(0, 1), new Range(3, 4));
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMultiContinuityWithinRange_noMatch() throws InvalidInputExcpeption {
        List<Double> data = Arrays.asList(2.0, 2.0, 1.0, 2.0, 2.0);
        List<Range> actual = searchUtil.searchMultiContinuityWithinRange(data, 0, 4, 1.0, 1.1, 2);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void testSearchMultiContinuityWithinRange_invalidInput() {
        List<Double> data = Arrays.asList(2.0, 2.0, 2.0, 2.0);
        try {
            searchUtil.searchMultiContinuityWithinRange(data, 3, 4, 0, 1, 1);
            fail("No exception thrown");
        } catch (InvalidInputExcpeption e) {
        }
    }

}