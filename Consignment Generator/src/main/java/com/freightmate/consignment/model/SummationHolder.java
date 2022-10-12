package com.freightmate.consignment.model;

public class SummationHolder
{
    private int evenSum;
    private int oddSum;

    public SummationHolder(int evenSum, int oddSum) {
        this.evenSum = evenSum;
        this.oddSum = oddSum;
    }

    public int getEvenSum() {
        return evenSum;
    }

    public void setEvenSum(int evenSum) {
        this.evenSum = evenSum;
    }

    public int getOddSum() {
        return oddSum;
    }

    public void setOddSum(int oddSum) {
        this.oddSum = oddSum;
    }
}
