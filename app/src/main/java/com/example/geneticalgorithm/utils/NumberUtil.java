package com.example.geneticalgorithm.utils;

//Степень 2-ки
public class NumberUtil {
    public static int getChromosomeSize(int num) {
        int size = 0;
        while (num > 0) {
            num >>= 1;
            size++;
        }
        return size;
    }

    //
    public static Double getChromosomeValue(String s, double minInterval, double maxInterval) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum += Character.getNumericValue(chars[i]) * Math.pow(2, chars.length - i - 1);
        }
        return minInterval + sum * ((maxInterval - minInterval) / (Math.pow(2, chars.length) - 1 ));
    }
}
