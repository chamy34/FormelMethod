package org.example;

import java.util.Random;


public class MatriceApproach {

    // Method to generate random numbers from a binomial distribution
    public static int[] generateBinomialRandomNumbers(int trials, double probability, int n) {
        int[] numbers = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < trials; j++) {
                if (random.nextDouble() < probability) {
                    count++;
                }
            }
            numbers[i] = count;
        }
        return numbers;
    }

    // Method to calculate mean of an array
    public static double calculateMean(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }

    // Method to calculate variance of an array
    public static double calculateVariance(int[] arr) {
        double mean = calculateMean(arr);
        double sum = 0;
        for (int num : arr) {
            sum += Math.pow(num - mean, 2);
        }
        return sum / arr.length;
    }

    // Method to generate matrices using the Matrix Approach
    public static int[][][] generateMatrices(int numMatrices, int numRows, int numCols, int[] sampleSizes) {
        int[][][] matrices = new int[numMatrices][numRows][numCols];

        // Loop through each matrix
        for (int k = 0; k < numMatrices; k++) {
            int sampleSize = sampleSizes[k];

            // Loop through each mean
            for (int i = 0; i < numRows; i++) {
                int trials = 10 * (i + 1);
                double probability = 0.1 * (i + 1);

                // Loop through each variance
                for (int j = 0; j < numCols; j++) {
                    double variance = 0.1 * (j + 1);

                    // Generate random numbers
                    int[] randomNumbers = generateBinomialRandomNumbers(trials, probability, sampleSize);

                    // Store mean and variance in the matrix
                    matrices[k][i][j] = randomNumbers[0];
                }
            }
        }

        return matrices;
    }

    public static void main(String[] args) {
        int numMatrices = 3; // Number of matrices
        int numRows = 5; // Number of rows (means)
        int numCols = 4; // Number of columns (variances)
        int[] sampleSizes = {1000, 2000, 3000}; // Different sample sizes

        int numExperiments = 10; // Number of experiments to perform

        long totalExecutionTime = 0;

        // Perform experiments
        for (int exp = 0; exp < numExperiments; exp++) {
            long startTime = System.nanoTime();

            // Generate matrices using the Matrix Approach
            generateMatrices(numMatrices, numRows, numCols, sampleSizes);

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            totalExecutionTime += executionTime;

            System.out.println("Experiment " + (exp + 1) + " execution time: " + executionTime + " nanoseconds");
        }

        // Calculate average execution time
        long averageExecutionTime = totalExecutionTime / numExperiments;
        System.out.println("Average execution time over " + numExperiments + " experiments: " + averageExecutionTime + " nanoseconds");
    }
}
