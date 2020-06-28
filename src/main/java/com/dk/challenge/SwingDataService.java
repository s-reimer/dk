package com.dk.challenge;

import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Paths;
import java.util.List;

public class SwingDataService {

    @Autowired
    private SearchUtil searchUtil;

    private SwingData swingData = null;

    public void loadDataFile(String filePath) {
        swingData = new SwingData(Paths.get(filePath));
    }

    public int searchContinuityAboveValue(String column, int indexBegin, int indexEnd,
                                          double threshold, int winLength) throws InvalidInputExcpeption {
        List<Double> data = getDataForColumn(column);
        return searchUtil.searchContinuityAboveValue(data, indexBegin, indexEnd, threshold, winLength);
    }

    public int backSearchContinuityWithinRange(String column, int indexBegin, int indexEnd,
                                               double thresholdLo, double thresholdHi, int winLength) throws InvalidInputExcpeption {
        List<Double> data = getDataForColumn(column);
        return searchUtil.backSearchContinuityWithinRange(data, indexBegin, indexEnd, thresholdLo, thresholdHi, winLength);
    }

    public int searchContinuityAboveValueTwoSignals(String column1, String column2, int indexBegin, int indexEnd,
                                                    double threshold1, double threshold2, int winLength) throws InvalidInputExcpeption {
        List<Double> data1 = getDataForColumn(column1);
        List<Double> data2 = getDataForColumn(column2);
        return searchUtil.searchContinuityAboveValueTwoSignals(data1, data2, indexBegin, indexEnd, threshold1, threshold2,
                winLength);
    }

    public List<Range> searchMultiContinuityWithinRange(String column, int indexBegin, int indexEnd,
                                                        double thresholdLo, double thresholdHi, int winLength) throws InvalidInputExcpeption {
        List<Double> data = getDataForColumn(column);
        return searchUtil.searchMultiContinuityWithinRange(data, indexBegin, indexEnd, thresholdLo, thresholdHi,
                winLength);
    }

    private List<Double> getDataForColumn(String name) {
        List<Double> data = null;
        switch (name) {
            case "ax":
                data = swingData.getAccelX();
                break;
            case "ay":
                data = swingData.getAccelY();
                break;
            case "az":
                data = swingData.getAccelZ();
                break;
            case "wx":
                data = swingData.getGyroX();
                break;
            case "wy":
                data = swingData.getGyroY();
                break;
            case "wz":
                data = swingData.getGyroZ();
                break;
        }
        return data;
    }
}
