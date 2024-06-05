/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpolationproject;

public class Interpolation {

    // Function to calculate Lagrange Polynomial
    public static double lagrangeInterpolation(double[] x, double[] y, double xi) {
        int n = x.length;
        double result = 0.0;

        for (int i = 0; i < n; i++) {
            double term = y[i];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    term = term * (xi - x[j]) / (x[i] - x[j]);
                }
            }
            result += term;
        }

        return result;
    }

    // Function to calculate divided differences table
    public static double[][] dividedDiffTable(double[] x, double[] y) {
        int n = x.length;
        double[][] table = new double[n][n];

        for (int i = 0; i < n; i++) {
            table[i][0] = y[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                table[i][j] = (table[i + 1][j - 1] - table[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        return table;
    }

    // Function to calculate Newton Polynomial
    public static double newtonInterpolation(double[] x, double[] y, double xi) {
        int n = x.length;
        double result = y[0];
        double[] factors = new double[n];
        double[][] table = dividedDiffTable(x, y);

        for (int i = 1; i < n; i++) {
            factors[i] = table[0][i];
            double term = factors[i];
            for (int j = 0; j < i; j++) {
                term *= (xi - x[j]);
            }
            result += term;
        }

        return result;
    }

    public static void main(String[] args) {
        double[] x = {5, 10, 15, 20, 25, 30, 35, 40};
        double[] y = {40, 30, 25, 40, 18, 20, 22, 15};

        System.out.println("Lagrange Interpolation:");
        for (double xi = 5; xi <= 40; xi += 1) {
            System.out.println("Interpolated value at x = " + xi + " is " + lagrangeInterpolation(x, y, xi));
        }

        System.out.println("\nNewton Interpolation:");
        for (double xi = 5; xi <= 40; xi += 1) {
            System.out.println("Interpolated value at x = " + xi + " is " + newtonInterpolation(x, y, xi));
        }
    }
}
