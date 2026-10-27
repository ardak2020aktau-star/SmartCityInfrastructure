package experiment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlotGenerator {
    public static void generate() throws IOException {
        Path csv = Path.of("results", "tables", "benchmark_results.csv");
        List<String> lines = Files.readAllLines(csv);
        List<Integer> vertices = new ArrayList<>();
        List<Integer> edges = new ArrayList<>();
        List<Double> kruskal = new ArrayList<>();
        List<Double> prim = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            String algorithm = parts[0];
            int v = Integer.parseInt(parts[2]);
            int e = Integer.parseInt(parts[3]);
            double time = Double.parseDouble(parts[4]);
            if (algorithm.equals("Kruskal")) {
                vertices.add(v);
                edges.add(e);
                kruskal.add(time);
            } else if (algorithm.equals("Prim")) {
                prim.add(time);
            }
        }
        Path output = Path.of("results", "plots");
        Files.createDirectories(output);
        drawChart(vertices, kruskal, prim,
                "Execution Time vs Number of Vertices",
                "Number of Vertices",
                output.resolve("time_vs_vertices.png"));
        drawChart(edges, kruskal, prim,
                "Execution Time vs Number of Edges",
                "Number of Edges",
                output.resolve("time_vs_edges.png"));
        System.out.println("Plots saved to: " + output.toAbsolutePath());
    }
    private static void drawChart(
            List<Integer> xValues,
            List<Double> kruskal,
            List<Double> prim,
            String title,
            String xLabel,
            Path file) throws IOException {
        int width = 900;
        int height = 600;
        int left = 100;
        int right = 60;
        int top = 90;
        int bottom = 100;
        BufferedImage image = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(title, left, 40);
        g.drawLine(left, top, left, height - bottom);
        g.drawLine(left, height - bottom,
                width - right, height - bottom);
        double maxTime = 0;
        for (double value : kruskal) {
            maxTime = Math.max(maxTime, value);
        }
        for (double value : prim) {
            maxTime = Math.max(maxTime, value);
        }
        if (maxTime == 0) maxTime = 1;
        int minX = xValues.get(0);
        int maxX = xValues.get(xValues.size() - 1);
        g.setFont(new Font("Arial", Font.PLAIN, 13));
        for (int i = 0; i <= 5; i++) {
            int y = height - bottom
                    - i * (height - top - bottom) / 5;
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(left, y, width - right, y);
            g.setColor(Color.BLACK);
            g.drawString(String.format("%.2f", maxTime * i / 5),
                    40, y + 5);
        }
        drawSeries(g, xValues, kruskal, Color.BLUE,
                minX, maxX, maxTime,
                left, right, top, bottom, width, height);
        drawSeries(g, xValues, prim, Color.RED,
                minX, maxX, maxTime,
                left, right, top, bottom, width, height);
        for (int x : xValues) {
            int px = left + (int) ((x - minX)
                    / (double) (maxX - minX)
                    * (width - left - right));
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(x), px - 10,
                    height - bottom + 25);
        }
        g.drawString(xLabel, width / 2 - 60, height - 35);
        g.drawString("Time (ms)", 15, top - 20);
        g.setColor(Color.BLUE);
        g.drawString("Kruskal", width - 200, 55);
        g.setColor(Color.RED);
        g.drawString("Prim", width - 100, 55);
        g.dispose();
        ImageIO.write(image, "png", file.toFile());
    }
    private static void drawSeries(
            Graphics2D g,
            List<Integer> xValues,
            List<Double> values,
            Color color,
            int minX, int maxX, double maxTime,
            int left, int right, int top, int bottom,
            int width, int height) {
        g.setColor(color);
        g.setStroke(new BasicStroke(3));
        int previousX = -1;
        int previousY = -1;
        for (int i = 0; i < xValues.size(); i++) {
            int x = left + (int) ((xValues.get(i) - minX)
                    / (double) (maxX - minX)
                    * (width - left - right));
            int y = height - bottom
                    - (int) (values.get(i) / maxTime
                    * (height - top - bottom));
            if (previousX != -1) {
                g.drawLine(previousX, previousY, x, y);
            }
            g.fillOval(x - 5, y - 5, 10, 10);
            previousX = x;
            previousY = y;
        }
    }
}