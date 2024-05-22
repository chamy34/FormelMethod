package org.example;

import java.util.Arrays;
import java.util.Random;

public class BinomialMergeSort {

    // Method to generate random numbers from a binomial distribution
    public static int[] generateBinomialRandomNumbers(int n, int trials, double probability) {
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

    // Merge Sort Algorithm
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, arr);
    }

    private static void merge(int[] left, int[] right, int[] arr) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
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

    public static long[] runExperiments(int numExperiments, int[] sampleSizes, int[] trialsArr, double[] probabilityArr) {
        long[] executionTimes = new long[numExperiments];

        for (int i = 0; i < numExperiments; i++) {
            int[] randomNumbers = generateBinomialRandomNumbers(sampleSizes[i], trialsArr[i], probabilityArr[i]);
            long startTime = System.nanoTime();
            mergeSort(randomNumbers);
            long endTime = System.nanoTime();
            executionTimes[i] = endTime - startTime;
        }

        return executionTimes;
    }

    public static void main(String[] args) {
        int n = 1000; // Sample size
        int[] trialsArr = {10, 20, 30}; // Different values of number of trials
        double[] probabilityArr = {0.2, 0.5, 0.8}; // Different values of probability of success
        int numExperiments = 3; // Number of experiments
        int[] sampleSizes = {1000, 2000, 3000}; // Different sample sizes

        for (int trials : trialsArr) {
            for (double probability : probabilityArr) {
                int[] randomNumbers = generateBinomialRandomNumbers(n, trials, probability);
                mergeSort(randomNumbers);
                double mean = calculateMean(randomNumbers);
                double variance = calculateVariance(randomNumbers);
                System.out.println("Mean: " + mean + ", Variance: " + variance +
                        ", Trials: " + trials + ", Probability: " + probability);
            }
        }

        long[] executionTimes = runExperiments(numExperiments, sampleSizes, trialsArr, probabilityArr);

        // Print execution times for each experiment
        System.out.println("Execution Times:");
        for (long time : executionTimes) {
            System.out.println(time);
        }
    }
}
