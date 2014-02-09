package com.donnfelker.android.bootstrap.core;

import org.math.array.LinearAlgebra;

/**
 * Created by ismet on 1/17/14.
 */
public class DistanceUtils {

    public static void sampleEuclideanCalculation() throws Exception {
        int[] a = {1, 3, 2, 4, 1};
        int[] b = {4, 5, 3, 1, 5};
        double distance = euclideanDistance(a, b);
        System.out.printf("Euclidean Distance:" + distance);
    }

    public static void sampleManhattanCalculation() throws Exception {
        int[] a = {1, 3, 2, 4, 1};
        int[] b = {4, 5, 3, 1, 5};
        double distance = manhattanDistance(a, b);
        System.out.printf("Manhattan Distance:" + distance);
    }

    public static double euclideanDistance(int[] vector1, int[] vector2) throws Exception {
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

    public static double manhattanDistance(int[] vector1, int[] vector2) throws Exception {
        if (vector1.length == vector2.length) {
            Double sum = 0D;
            for (int i = 0; i < vector1.length; i++) {
                sum = sum
                        + Math.abs(Integer.valueOf(vector2[i]).doubleValue() - Integer.valueOf(vector1[i]).doubleValue()) ;
            }
            return sum;
        } else {
            throw new Exception("Exception in Manhattan distance: array lengths are not equal");
        }
    }

    public static double mahalanobisDistance(int[] vector1, int[] vector2){
        double[][] S;
        double[] x = convertToDoubleArray(vector1);
        double[] y = convertToDoubleArray(vector2);
        int dim = vector1.length;
            S = new double[dim][dim];
            for(int i=0; i<dim; i++)
                for(int j=0; j<dim; j++)
                    if(i == j)
                        S[i][j] = 1.0;
                    else
                        S[i][j] = 0.0;

        return getSimilarity(x,y,S);
    }
    public static double getDistance(double[] x, double[] y, double[][] S) {
        double[][] diff = new double[1][x.length];
        for(int i=0; i<x.length; i++)
            diff[0][i] = x[i] - y[i];
        double result[][] = LinearAlgebra.times(diff, LinearAlgebra.inverse(S));
        result = LinearAlgebra.times( result, LinearAlgebra.transpose(diff) );
        return Math.sqrt( result[0][0] );
    }
    public static double getSimilarity(double[] x, double[] y, double[][] S) {
        return 1.0 / (1.0 + getDistance(x, y, S));
    }

    public static double[] convertToDoubleArray(int[] intArray){
        double[] result = new double[intArray.length];
        for (int i = 0; i < intArray.length; i++){
            result[i] = (double)intArray[i];
        }
        return result;
    }
}
