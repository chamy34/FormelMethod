package org.example;

import java.util.Random;

public class MM {



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

            for (int k = 0; k < numMatrices; k++) {
                long startTime = System.nanoTime(); // Start timing for this matrix

                int sampleSize = sampleSizes[k];

                for (int i = 0; i < numRows; i++) {
                    int trials = 10 * (i + 1);
                    double probability = 0.1 * (i + 1);

                    for (int j = 0; j < numCols; j++) {
                        double variance = 0.1 * (j + 1);

                        int[] randomNumbers = generateBinomialRandomNumbers(trials, probability, sampleSize);

                        matrices[k][i][j] = randomNumbers[0];
                    }
                }

                long endTime = System.nanoTime(); // End timing for this matrix
                long executionTime = endTime - startTime;

                // Output the matrix and its execution time
                System.out.println("Matrix " + (k + 1) + ":");
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        System.out.print(matrices[k][i][j] + "\t");
                    }
                    System.out.println();
                }
                System.out.println("Execution time: " + executionTime + " nanoseconds");

                // Calculate theoretical complexity cases
                double theoreticalComplexity = calculateTheoreticalComplexity(numRows, numCols, sampleSize);

                // Compare with measured execution time
                System.out.println("Theoretical complexity: " + theoreticalComplexity);
                System.out.println("Comparison with measured execution time: " + (executionTime <= theoreticalComplexity ? "Good" : "Bad") + "\n");
            }

            return matrices;
        }

        // Method to calculate theoretical complexity based on problem parameters
        public static double calculateTheoreticalComplexity(int numRows, int numCols, int sampleSize) {
            // Example calculation: O(numRows * numCols * sampleSize)
            return numRows * numCols * sampleSize;
        }

        public static void main(String[] args) {
            int numMatrices = 3; // Number of matrices
            int numRows = 5; // Number of rows (means)
            int numCols = 4; // Number of columns (variances)
            int[] sampleSizes = {1000, 2000, 3000}; // Different sample sizes

            generateMatrices(numMatrices, numRows, numCols, sampleSizes);
        }
    }


