import java.util.Random;
import java.util.Scanner;

public class MatrixOperations {
    private static final int MAX_SIZE = 20;
    private static final int RANDOM_MIN = 1;
    private static final int RANDOM_MAX = 100;

    public static void main(String[] args) {
        int width, height;
        int[][] matrix;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ширину матриці (не більше " + MAX_SIZE + "): ");
        width = scanner.nextInt();

        System.out.print("Введіть висоту матриці (не більше " + MAX_SIZE + "): ");
        height = scanner.nextInt();

        if (width <= 0 || width > MAX_SIZE || height <= 0 || height > MAX_SIZE) {
            System.out.println("Некоректні розміри матриці.");
            return;
        }

        System.out.println("Оберіть спосіб створення матриці:");
        System.out.println("1. Ввести з клавіатури");
        System.out.println("2. Згенерувати випадковим чином");
        int choice = scanner.nextInt();

        if (choice == 1) {
            matrix = readMatrixFromInput(width, height, scanner);
        } else if (choice == 2) {
            matrix = generateRandomMatrix(width, height);
        } else {
            System.out.println("Некоректний вибір.");
            return;
        }

        System.out.println("Матриця:");
        printMatrix(matrix);

        int minValue = findMinValue(matrix);
        int maxValue = findMaxValue(matrix);
        double average = calculateAverage(matrix);

        System.out.println("Мінімальне значення: " + minValue);
        System.out.println("Максимальне значення: " + maxValue);
        System.out.println("Середнє арифметичне: " + average);
    }

    private static int[][] readMatrixFromInput(int width, int height, Scanner scanner) {
        int[][] matrix = new int[height][width];

        System.out.println("Введіть елементи матриці:");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("Елемент [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }

    private static int[][] generateRandomMatrix(int width, int height) {
        int[][] matrix = new int[height][width];
        Random random = new Random();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = random.nextInt(RANDOM_MAX - RANDOM_MIN + 1) + RANDOM_MIN;
            }
        }

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int findMinValue(int[][] matrix) {
        int minValue = Integer.MAX_VALUE;

        for (int[] row : matrix) {
            for (int value : row) {
                if (value < minValue) {
                    minValue = value;
                }
            }
        }

        return minValue;
    }

    private static int findMaxValue(int[][] matrix) {
        int maxValue = Integer.MIN_VALUE;

        for (int[] row : matrix) {
            for (int value : row) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }

        return maxValue;
    }

    private static double calculateAverage(int[][] matrix) {
        int sum = 0;
        int count = 0;

        for (int[] row : matrix) {
            for (int value : row) {
                sum += value;
                count++;
            }
        }

        if (count == 0) {
            return 0; // Щоб уникнути ділення на нуль
        }

        return (double) sum / count;
    }
}
