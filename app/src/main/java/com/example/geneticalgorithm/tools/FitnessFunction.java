package com.example.geneticalgorithm.tools;

public class FitnessFunction {
    public static double value(double x) {
        return Math.pow(x, 4) - 250;
    }
}
