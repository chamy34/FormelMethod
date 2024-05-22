package org.example;

import java.util.Arrays;

public class ExecutionTimeMatrix {

    // Method to calculate execution time for each mean and variance pair using merge sort with binomial distribution
    public static long[][] calculateExecutionTime(double[] means, double[] variances) {
        int numMeans = means.length;
        int numVariances = variances.length;
        long[][] executionTimes = new long[numVariances][numMeans];

        // Sample size for sorting
        int n = 2000;

        // Different values of number of trials
        int[] trialsArr = {10, 20, 30};

        // Different values of probability of success
        double[] probabilityArr = {0.2, 0.5, 0.8};

        for (int i = 0; i < numVariances; i++) {
            for (int j = 0; j < numMeans; j++) {
                // Generate random numbers from binomial distribution
                int[] randomNumbers = BinomialMergeSort.generateBinomialRandomNumbers(n, trialsArr[j], probabilityArr[i]);

                // Record start time
                long startTime = System.nanoTime();

                // Perform merge sort
                BinomialMergeSort.mergeSort(randomNumbers);

                // Record end time
                long endTime = System.nanoTime();

                // Calculate execution time
                executionTimes[i][j] = endTime - startTime;
            }
        }

        return executionTimes;
    }

    public static void main(String[] args) {
        // Example data
        double[] means = {1.972, 4.998, 8.031}; // Add more mean values, 3.917, 9.964, 2.0, 5.0
        double[] variances = {1.5932159999999866, 2.437995999999996, 1.62403899999998}; // Add more variance values, 3.072111000000002, 5.00470399999996, 2.0, 3.0

        // Calculate execution times
        long[][] executionTimes = calculateExecutionTime(means, variances);

        // Print the header
        System.out.print("       | ");
        for (double mean : means) {
            System.out.printf("%7.2f | ", mean);
        }
        System.out.println();
        System.out.println("----------------------------------------------");

        // Print the matrix
        for (int i = 0; i < executionTimes.length; i++) {
            System.out.printf("%.2f |", variances[i]);
            for (long executionTime : executionTimes[i]) {
                System.out.printf(" %7d", executionTime);
            }
            System.out.println();
        }
    }
}
