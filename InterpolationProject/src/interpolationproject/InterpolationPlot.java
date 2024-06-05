/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpolationproject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JFrame;

public class InterpolationPlot extends JFrame {

    public InterpolationPlot(String title) {
        super(title);

        // Create dataset
        XYSeriesCollection dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Interpolation Plot",
                "X-Axis",
                "Y-Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Customize the plot
        XYPlot plot = chart.getXYPlot();
        
        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYSeriesCollection createDataset() {
        double[] x = {5, 10, 15, 20, 25, 30, 35, 40};
        double[] y = {40, 30, 25, 40, 18, 20, 22, 15};

        XYSeries series1 = new XYSeries("Lagrange Interpolation");
        for (double xi = 5; xi <= 40; xi += 1) {
            series1.add(xi, Interpolation.lagrangeInterpolation(x, y, xi));
        }

        XYSeries series2 = new XYSeries("Newton Interpolation");
        for (double xi = 5; xi <= 40; xi += 1) {
            series2.add(xi, Interpolation.newtonInterpolation(x, y, xi));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    public static void main(String[] args) {
        JFrame frame = new InterpolationPlot("Interpolation Plot Example");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

