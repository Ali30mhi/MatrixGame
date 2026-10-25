public final class MatrixRotation {

    private MatrixRotation() { }

    /**
     * Returns a new matrix rotated by the given angle.
     * Positive angles rotate clockwise, negative angles counter-clockwise.
     * Any multiple of 90 is accepted (e.g. 450 or -630).
     * The input matrix is never modified.
     *
     * @throws IllegalArgumentException if the angle is not a multiple of 90
     *         or the matrix is null, empty or not rectangular
     */
    public static int[][] rotateMatrix(int[][] matrix, int degrees) {
        validate(matrix);
        if (degrees % 90 != 0) {
            throw new IllegalArgumentException("Angle must be a multiple of 90, got " + degrees);
        }

        int turns = Math.floorMod(degrees / 90, 4);   // clockwise quarter turns: 0..3
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] out = (turns % 2 == 0) ? new int[rows][cols] : new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = matrix[i][j];
                switch (turns) {
                    case 0 -> out[i][j] = value;                               // 0°
                    case 1 -> out[j][rows - 1 - i] = value;                    // 90° clockwise
                    case 2 -> out[rows - 1 - i][cols - 1 - j] = value;         // 180°
                    case 3 -> out[cols - 1 - j][i] = value;                    // 90° counter-clockwise
                    default -> throw new AssertionError(turns);
                }
            }
        }
        return out;
    }

    private static void validate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix must not be null or empty");
        }
        int cols = matrix[0].length;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length != cols) {
                throw new IllegalArgumentException(
                        "Row " + i + " has a different length; the matrix must be rectangular");
            }
        }
    }

    /** Prints the matrix with columns aligned, e.g. for negative or multi-digit numbers. */
    public static void printMatrix(int[][] matrix) {
        int width = 1;
        for (int[] row : matrix) {
            for (int v : row) {
                width = Math.max(width, String.valueOf(v).length());
            }
        }
        String cell = "%" + width + "d";
        for (int[] row : matrix) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                if (j > 0) line.append("  ");
                line.append(String.format(cell, row[j]));
            }
            System.out.println(line);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {
            { 1, 2, 3 },
            { 4, 5, 6 }
        };

        System.out.println("Original:");
        printMatrix(matrix);

        for (int degrees : new int[] { 90, -90, 180, 270, 360, 450 }) {
            System.out.println("Rotated " + degrees + "°:");
            printMatrix(rotateMatrix(matrix, degrees));
        }

        try {
            rotateMatrix(matrix, 45);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}