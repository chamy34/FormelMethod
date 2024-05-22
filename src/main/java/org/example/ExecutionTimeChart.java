package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ExecutionTimeChart extends JFrame {

    public ExecutionTimeChart(String title, double[] dataSizes, int[] trialsArr, double[] probabilityArr) {
        super(title);
        int totalCombinations = trialsArr.length * probabilityArr.length;

        // Initialize means, variances, and executionTimes arrays with the correct size
        double[] means = new double[totalCombinations];
        double[] variances = new double[totalCombinations];
        long[][] executionTimes = new long[totalCombinations][dataSizes.length];

        // Calculate means, variances, and execution times
        int idx = 0;
        for (int trials : trialsArr) {
            for (double probability : probabilityArr) {
                int[] randomNumbers = BinomialMergeSort.generateBinomialRandomNumbers((int)dataSizes[idx], trials, probability);
                BinomialMergeSort.mergeSort(randomNumbers);
                means[idx] = BinomialMergeSort.calculateMean(randomNumbers);
                variances[idx] = BinomialMergeSort.calculateVariance(randomNumbers);
                executionTimes[idx] = runExperiments(dataSizes, trials, probability);
                idx++;
            }
            idx = 0; // Reset idx after each iteration of the inner loop
        }

        // Create a dataset
        DefaultCategoryDataset dataset = createDataset(dataSizes, means, variances, executionTimes);

        // Create a chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Execution Time vs. Data Size, Mean, and Variance", // Chart title
                "Mean and Variance", // X-axis label
                "Execution Time (nanoseconds)", // Y-axis label
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Customize chart
        chart.setBackgroundPaint(Color.WHITE);

        // Create chart panel and add it to JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(double[] dataSizes, double[] means, double[] variances, long[][] executionTimes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int idx = 0;
        for (int i = 0; i < dataSizes.length; i++) {
            for (int j = 0; j < means.length; j++) {
                if (idx < executionTimes.length && i < executionTimes[idx].length) {
                    dataset.addValue(executionTimes[idx][i], "Data Size " + dataSizes[i], String.valueOf(means[j]));
                }
                idx++; // Increment idx to access the next execution time
            }
        }
        return dataset;
    }


    private long[] runExperiments(double[] dataSizes, int trials, double probability) {
        int numExperiments = dataSizes.length;
        long[] executionTimes = new long[numExperiments];
        int idx = 0; // Initialize idx variable
        for (int i = 0; i < numExperiments; i++) {
            int[] randomNumbers = BinomialMergeSort.generateBinomialRandomNumbers((int) dataSizes[i], trials, probability);
            long startTime = System.nanoTime();
            BinomialMergeSort.mergeSort(randomNumbers);
            long endTime = System.nanoTime();
            executionTimes[idx++] = endTime - startTime; // Store execution time and increment idx
        }
        return executionTimes;
    }

    public static void main(String[] args) {
        // Example data
        double[] dataSizes = {1000, 2000, 3000};
        int[] trialsArr = {10, 20, 30}; // Different values of number of trials
        double[] probabilityArr = {0.2, 0.5, 0.8}; // Different values of probability of success

        // Create and display the chart
        SwingUtilities.invokeLater(() -> {
            ExecutionTimeChart example = new ExecutionTimeChart("Execution Time Chart", dataSizes, trialsArr, probabilityArr);
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
