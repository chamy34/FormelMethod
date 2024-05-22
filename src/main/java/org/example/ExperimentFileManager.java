package org.example;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

    public class ExperimentFileManager {

        // Method to create CSV file with experiment results
        public static void createExperimentFile(String filename, ExperimentMetadata metadata, int[][][] matrices, long[] executionTimes, double[] theoreticalComplexities, String[] comparisons) {
            try (FileWriter writer = new FileWriter(filename)) {
                // Write metadata
                writer.write("Algorithm: " + metadata.getAlgorithm() + "\n");
                writer.write("Distribution: " + metadata.getDistribution() + "\n");
                writer.write("Parameters: " + metadata.getParameters() + "\n");
                writer.write("Timestamp: " + new Date() + "\n");
                writer.write("Number of experiments: " + metadata.getNumExperiments() + "\n");
                writer.write("JVM warm-up rounds: " + metadata.getJvmWarmUpRounds() + "\n\n");

                // Write experiment results
                for (int i = 0; i < metadata.getNumExperiments(); i++) {
                    writer.write("Experiment " + (i + 1) + ":\n");
                    writer.write("Execution time: " + executionTimes[i] + " nanoseconds\n");
                    writer.write("Theoretical complexity: " + theoreticalComplexities[i] + "\n");
                    writer.write("Comparison: " + comparisons[i] + "\n");

                    writer.write("Matrix:\n");
                    for (int j = 0; j < matrices[i].length; j++) {
                        for (int k = 0; k < matrices[i][j].length; k++) {
                            writer.write(matrices[i][j][k] + ",");
                        }
                        writer.write("\n");
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            // Example usage
            int numMatrices = 3; // Number of matrices
            int numRows = 5; // Number of rows (means)
            int numCols = 4; // Number of columns (variances)
            int[] sampleSizes = {1000, 2000, 3000}; // Different sample sizes

            // Generate matrices and metadata
            ExperimentMetadata metadata = new ExperimentMetadata("AlgorithmName", "DistributionName", "Parameters", numMatrices, 10);
            int[][][] matrices = MatriceApproach.generateMatrices(numMatrices, numRows, numCols, sampleSizes);
            long[] executionTimes = {1000, 2000, 3000}; // Example execution times
            double[] theoreticalComplexities = {5000, 6000, 7000}; // Example theoretical complexities
            String[] comparisons = {"Good", "Good", "Good"}; // Example comparisons

            // Create CSV file
            createExperimentFile("results_data_size_1000_mean_5_variance_2.csv", metadata, matrices, executionTimes, theoreticalComplexities, comparisons);
        }
    }

    class ExperimentMetadata {
        private String algorithm;
        private String distribution;
        private String parameters;
        private int numExperiments;
        private int jvmWarmUpRounds;

        // Constructor
        public ExperimentMetadata(String algorithm, String distribution, String parameters, int numExperiments, int jvmWarmUpRounds) {
            this.algorithm = algorithm;
            this.distribution = distribution;
            this.parameters = parameters;
            this.numExperiments = numExperiments;
            this.jvmWarmUpRounds = jvmWarmUpRounds;
        }

        // Getters
        public String getAlgorithm() {
            return algorithm;
        }

        public String getDistribution() {
            return distribution;
        }

        public String getParameters() {
            return parameters;
        }

        public int getNumExperiments() {
            return numExperiments;
        }

        public int getJvmWarmUpRounds() {
            return jvmWarmUpRounds;
        }
    }


