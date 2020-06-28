package com.dk.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwingData {

    private final List<Integer> time;
    private final List<Double> accelX;
    private final List<Double> accelY;
    private final List<Double> accelZ;
    private final List<Double> gyroX;
    private final List<Double> gyroY;
    private final List<Double> gyroZ;


    public SwingData(Path dataFilePath) {
        time = new ArrayList<>();
        accelX = new ArrayList<>();
        accelY = new ArrayList<>();
        accelZ = new ArrayList<>();
        gyroX = new ArrayList<>();
        gyroY = new ArrayList<>();
        gyroZ = new ArrayList<>();
        try {
            Files.lines(dataFilePath).map(line -> Arrays.asList(line.split(","))).forEach(this::loadRow);
        } catch (IOException e) {
            //TODO figure out the best way to fail
        }
    }

    private void loadRow(List<String> row) {
        time.add(Integer.parseInt(row.get(0)));
        accelX.add(Double.parseDouble(row.get(1)));
        accelY.add(Double.parseDouble(row.get(2)));
        accelZ.add(Double.parseDouble(row.get(3)));
        gyroX.add(Double.parseDouble(row.get(4)));
        gyroY.add(Double.parseDouble(row.get(5)));
        gyroZ.add(Double.parseDouble(row.get(6)));
    }

    public List<Integer> getTime() {
        return time;
    }

    public List<Double> getAccelX() {
        return accelX;
    }

    public List<Double> getAccelY() {
        return accelY;
    }

    public List<Double> getAccelZ() {
        return accelZ;
    }

    public List<Double> getGyroX() {
        return gyroX;
    }

    public List<Double> getGyroY() {
        return gyroY;
    }

    public List<Double> getGyroZ() {
        return gyroZ;
    }
}