package com.donnfelker.android.bootstrap.core;

/**
 * Created by ismet on 1/17/14.
 */
public class DistanceUtils {

    private static void sampleEuclideanCalculation() throws Exception {
        int[] a = {1, 3, 2, 4, 1};
        int[] b = {4, 5, 3, 1, 5};
        double distance = euclideanDistance(a, b);
        System.out.printf("Euclidean Distance:" + distance);
    }

    private static void sampleManhattanCalculation() throws Exception {
        int[] a = {1, 3, 2, 4, 1};
        int[] b = {4, 5, 3, 1, 5};
        double distance = manhattanDistance(a, b);
        System.out.printf("Manhattan Distance:" + distance);
    }

    private static double euclideanDistance(int[] vector1, int[] vector2) throws Exception {
        if (vector1.length == vector2.length) {
            Double sum = 0D;
            for (int i = 0; i < vector1.length; i++) {
                sum = sum
                        + (Integer.valueOf(vector2[i]).doubleValue() - Integer.valueOf(vector1[i]).doubleValue())
                        * (Integer.valueOf(vector2[i]).doubleValue() - Integer.valueOf(vector1[i]).doubleValue());
            }
            return Math.sqrt(sum);
        } else {
            throw new Exception("Exception in Euclidean distance: array lengths are not equal");
        }
    }

    private static double manhattanDistance(int[] vector1, int[] vector2) throws Exception {
        if (vector1.length == vector2.length) {
            Double sum = 0D;
            for (int i = 0; i < vector1.length; i++) {
                sum = sum
                        + Math.abs(Integer.valueOf(vector2[i]).doubleValue() - Integer.valueOf(vector1[i]).doubleValue());
            }
            return sum;
        } else {
            throw new Exception("Exception in Manhattan distance: array lengths are not equal");
        }
    }
}
