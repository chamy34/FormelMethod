package org.example;

public class SortingAlgorithmBenchmark {

    public static void main(String[] args) {
        int numExecutions = 1000; // Define the number of executions
        long totalExecutionTime = 0;

        // Perform sorting algorithm executions and measure time
        for (int i = 0; i < numExecutions; i++) {
            long startTime = System.nanoTime();

            int[] sampleData = generateSampleData();
            BinomialMergeSort.mergeSort(sampleData);

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            totalExecutionTime += executionTime;
        }

        // Calculate average time in milliseconds
        double averageTimeInMillis = (double) totalExecutionTime / numExecutions / 1_000_000.0;

        System.out.println("Average Time per Operation: " + averageTimeInMillis + " milliseconds");
    }

    private static int[] generateSampleData() {
            // Implement your method to generate sample data here
            return new int[1000]; // Replace this with your actual sample data
        }
    }

