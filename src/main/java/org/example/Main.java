package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.print("k = ");
        int k = scan.nextInt();
        System.out.print("n = ");
        int n = scan.nextInt();
        initMatrix(matrix, k, n);
        printMatrix(matrix, k, n);
        System.out.println();
        remakeInTriangle(matrix, k, n);
        printMatrix(matrix, k, n);
        System.out.print("Enter the value: ");
        int value = scan.nextInt();
        int count = countRowsWithAverageLessThan(matrix, value);
        System.out.println("Number of rows with average less than " + value + ": " + count);
    }

    public static double averageOfRow(List<Integer> row) {
        int sum = 0;
        for (int num : row) {
            sum += num;
        }
        return (double) sum / row.size();
    }

    public static int countRowsWithAverageLessThan(List<List<Integer>> matrix, int value) {
        int count = 0;
        for (List<Integer> row : matrix) {
            if (averageOfRow(row) < value) {
                count++;
            }
        }
        return count;
    }

    public static void remakeInTriangle(List<List<Integer>> matrix, int k, int n) {
        for (int i = 0; i < Math.min(k, n); i++) {
            // Знайти ненульовий елемент в поточному стовпці
            int pivotRow = -1;
            for (int j = i; j < k; j++) {
                if (matrix.get(j).get(i) != 0) {
                    pivotRow = j;
                    break;
                }
            }
            // Якщо ненульового елементу немає, перейти до наступного стовпця
            if (pivotRow == -1) {
                continue;
            }

            // Обміняти рядок із ненульовим елементом та перший рядок
            List<Integer> temp = matrix.get(i);
            matrix.set(i, matrix.get(pivotRow));
            matrix.set(pivotRow, temp);

            // Привести решту рядків до трикутного вигляду, використовуючи операції рядкової арифметики
            for (int j = i + 1; j < k; j++) {
                double factor = (double) matrix.get(j).get(i) / matrix.get(i).get(i);
                for (int l = i; l < n; l++) {
                    matrix.get(j).set(l, (int) (matrix.get(j).get(l) - factor * matrix.get(i).get(l)));
                }
            }
        }
    }

    public static void initMatrix(List<List<Integer>> matrix, int k, int n) {
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                matrix.get(i).add(rand.nextInt(61) - 35);
            }
        }
    }

    public static void printMatrix(List<List<Integer>> matrix, int k, int n) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%5s", matrix.get(i).get(j));
            }
            System.out.println();
        }
    }
}