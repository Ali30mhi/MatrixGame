# MatrixGame — Matrix Rotation in Java

Rotate any rectangular integer matrix by any multiple of 90°,
clockwise or counter-clockwise.

## Features

- Works with square and non-square (rectangular) matrices
- Accepts any multiple of 90: `90`, `-90`, `180`, `270`, `360`, `450`, `-630`, ...
- Positive angles rotate clockwise, negative angles counter-clockwise
- Always returns a new matrix — the input is never modified
- Clear errors for invalid angles (e.g. `45`) and empty or jagged matrices
- Pretty printing with aligned columns

## Requirements

Java 14 or newer (uses `switch` with `->`).

## Run

```bash
javac MatrixRotation.java
java MatrixRotation
```

## Usage

```java
int[][] matrix = {
    { 1, 2, 3 },
    { 4, 5, 6 }
};

int[][] rotated = MatrixRotation.rotateMatrix(matrix, 90);
MatrixRotation.printMatrix(rotated);
```

Output:

```
4  1
5  2
6  3
```

## How it works

The angle is reduced to a number of clockwise quarter turns (0–3) with
`Math.floorMod(degrees / 90, 4)`, so `450` behaves like `90` and `-90`
like `270`. Each element `matrix[i][j]` of a `rows × cols` matrix is then
moved to its new position:

| Turns | Rotation              | New position                   |
|-------|-----------------------|--------------------------------|
| 0     | 0°                    | `[i][j]`                       |
| 1     | 90° clockwise         | `[j][rows - 1 - i]`            |
| 2     | 180°                  | `[rows - 1 - i][cols - 1 - j]` |
| 3     | 90° counter-clockwise | `[cols - 1 - j][i]`            |
