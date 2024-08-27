import java.util.*;

public class MatrixRotation {

    public static int[][] rotateMatrix(int[][] matrix, int degree) {
        int[][] rotated;
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (degree == 90 || degree == -270) {
            rotated = new int[cols][rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    rotated[j][rows - i - 1] = matrix[i][j];
                }
            }
        } else if (degree == -90 || degree == 270) {
            rotated = new int[cols][rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    rotated[cols - j - 1][i] = matrix[i][j];
                }
            }
        } else if (Math.abs(degree) == 180) {
            rotated = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    rotated[rows - i - 1][cols - j - 1] = matrix[i][j];
                }
            }
        } else {
            return matrix;
        }

        return rotated;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
 
