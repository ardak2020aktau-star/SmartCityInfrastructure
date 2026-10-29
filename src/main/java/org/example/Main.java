package org.example;
import experiment.PlotGenerator;
import experiment.ResultsExporter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String mode = args.length == 0 ? "all" : args[0];
        switch (mode) {
            case "all" -> {
                ResultsExporter.export();
                PlotGenerator.generate();
            }
            case "benchmark" -> ResultsExporter.export();
            case "plots" -> PlotGenerator.generate();
            default -> throw new IllegalArgumentException(
                    "Usage: Main [all|benchmark|plots]");
        }
    }
}