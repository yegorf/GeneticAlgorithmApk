package com.example.geneticalgorithm.tools;

import com.example.geneticalgorithm.utils.NumberUtil;

public class Chromosome {
    private String binary = null;
    private Double decimal = null;
    private Double functionValue;
    private int copiesCount;

    public Chromosome() {

    }

    public Chromosome(String binary) {
        this.binary = binary;
    }

    public void setValue(double minInterval, double maxInterval) {
        this.decimal = NumberUtil.getChromosomeValue(this.binary, minInterval, maxInterval);
    }

    private Chromosome(String binary, Double decimal, Double functionValue, int copiesCount) {
        this.binary = binary;
        this.decimal = decimal;
        this.functionValue = functionValue;
        this.copiesCount = copiesCount;
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }

    public Double getDecimal() {
        return decimal;
    }

    public void setDecimal(Double decimal) {
        this.decimal = decimal;
    }

    public Double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(Double functionValue) {
        this.functionValue = functionValue;
    }

    public int getCopiesCount() {
        return copiesCount;
    }

    public void setCopiesCount(int copiesCount) {
        this.copiesCount = copiesCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Chromosome that = (Chromosome) o;

        return binary.equals(that.binary);
    }

    @Override
    public int hashCode() {
        return binary.hashCode();
    }

    public void reborn(String binary) {
        this.binary = binary;
        this.decimal = null;
        this.functionValue = null;
    }

    public Chromosome copy() {
        return new Chromosome(binary, decimal, functionValue, copiesCount);
    }
}
