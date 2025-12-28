import java.util.Arrays;
import java.util.Comparator;

public class TestMatrixOperations {
    public static void main(String[] args) {

        // Test subMatrix
        double[][] arr1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        FlatMatrix fm1 = new FlatMatrix(arr1);
        testSubMatrix(null, 1, 1, "IllegalArgumentException");
        testSubMatrix(fm1, -1, 1, "IllegalArgumentException");
        testSubMatrix(fm1, 2, 1, "IllegalArgumentException");
        testSubMatrix(fm1, 1, 4, "IllegalArgumentException");
        testSubMatrix(fm1, 0, 2, "(1.0 2.0 3.0)(4.0 5.0 6.0)");

        // Test normalize
        testNormalize(null, "IllegalArgumentException");
        double[][] arr2 = {{1, 150000}, {3, 149000}, {2.8, 155000}, {1.2, 150348}};
        FlatMatrix fm2 = new FlatMatrix(arr2);
        testNormalize(fm2, "(-1.1043 -0.47)(1.1043 -0.9024)(0.8835 1.6919)(-0.8835 -0.3195)");

        // Test squareDistance
        testSquareDistance(null, "IllegalArgumentException");
        double[][] arr3 = {{1}, {3}};
        FlatMatrix fm3 = new FlatMatrix(arr3);
        testSquareDistance(fm3, "(0.0 4.0)(4.0 0.0)");
        double[][] arr4 = {{1, 2}, {4, 0}};
        FlatMatrix fm4 = new FlatMatrix(arr4);
        testSquareDistance(fm4, "(0.0 13.0)(13.0 0.0)");

        // Test multiply
        double[][] arr5 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        FlatMatrix fm5 = new FlatMatrix(arr5);
        testMultiply(null, fm5, "IllegalArgumentException");
        testMultiply(fm5, null, "IllegalArgumentException");
        double[][] arr6 = {{1, 2}, {3, 4}, {5, 6}};
        FlatMatrix fm6 = new FlatMatrix(arr6);
        double[][] arr7 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        FlatMatrix fm7 = new FlatMatrix(arr7);
        testMultiply(fm6, fm7, "IllegalArgumentException");
        double[][] arr8 = {{2, 1}, {1, 4}, {4, 5}};
        FlatMatrix fm8 = new FlatMatrix(arr8);
        double[][] arr9 = {{1, 2, 0}, {0, 1, 2}};
        FlatMatrix fm9 = new FlatMatrix(arr9);
        testMultiply(fm8, fm9, "(2.0 5.0 2.0)(1.0 6.0 8.0)(4.0 13.0 10.0)");
        double[][] arr10 = {{1, 2}, {3, 4}};
        FlatMatrix fm10 = new FlatMatrix(arr10);
        double[][] arr11 = {{5, 6, 7}, {8, 9, 10}};
        FlatMatrix fm11 = new FlatMatrix(arr11);
        testMultiply(fm10, fm11, "(21.0 24.0 27.0)(47.0 54.0 61.0)");  // Sponsored by Daniel Sabbag

        // Test diagonal
        testDiagonal(null, "IllegalArgumentException");
        double[][] arr12 = {{1, 2}, {3, 4}, {5, 6}};
        FlatMatrix fm12 = new FlatMatrix(arr12);
        testDiagonal(fm12, "IllegalArgumentException");
        double[][] arr13 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        FlatMatrix fm13 = new FlatMatrix(arr13);
        testDiagonal(fm13, "(1.0 0.0 0.0)(0.0 5.0 0.0)(0.0 0.0 9.0)");

        // Test add
        double[][] arr14 = {{1, 2}, {3, 4}, {5, 6}};
        FlatMatrix fm14 = new FlatMatrix(arr14);
        testAdd(null, fm14, "IllegalArgumentException");
        testAdd(fm14, null, "IllegalArgumentException");
        double[][] arr15 = {{1, 2}, {3, 4}};
        FlatMatrix fm15 = new FlatMatrix(arr15);
        testAdd(fm14, fm15, "IllegalArgumentException");
        double[][] arr16 = {{1, 2, 3}, {4, 5, 6}};
        FlatMatrix fm16 = new FlatMatrix(arr16);
        testAdd(fm15, fm16, "IllegalArgumentException");
        double[][] arr17 = {{1, 2}, {3, 4}, {5, 6}};
        FlatMatrix fm17 = new FlatMatrix(arr17);
        double[][] arr18 = {{1, 1}, {1, 1}, {1, 1}};
        FlatMatrix fm18 = new FlatMatrix(arr18);
        testAdd(fm17, fm18, "(2.0 3.0)(4.0 5.0)(6.0 7.0)");

        // Test sub
        testSub(null, fm14, "IllegalArgumentException");
        testSub(fm14, null, "IllegalArgumentException");
        testSub(fm14, fm15, "IllegalArgumentException");
        testSub(fm15, fm16, "IllegalArgumentException");
        testSub(fm17, fm18, "(0.0 1.0)(2.0 3.0)(4.0 5.0)");

        // Test selectionSort
        double[][] arr19 = {{5, 3, 1}, {3, 1, 2}};
        FlatMatrix fm19 = new FlatMatrix(arr19);
        IndexComparator ic = new IndexComparator(fm19, 1);
        int[] indices = {0, 1, 2};
        testSelectionSort(null, ic, "IllegalArgumentException");
        testSelectionSort(indices, null, "IllegalArgumentException");
        testSelectionSort(indices, ic, "[1, 2, 0]");

        // Test testClasses
        double[][] arr20 = {{0}, {0.1}, {0.2}, {10}, {10.1}, {10.2}};
        FlatMatrix fm20 = new FlatMatrix(arr20);
        boolean[] boolArr1 = {false, false, false, true, true, true};
        double[][] arr21 = {{0.05}, {10.05}};
        FlatMatrix fm21 = new FlatMatrix(arr21);
        testTestClasses(fm20, boolArr1, fm21, 1, "[false, true]");
        double[][] arr22 = {{4.9}, {5.1}, {5.2}};
        FlatMatrix fm22 = new FlatMatrix(arr22);
        boolean[] boolArr2 = {false, true, true};
        double[][] arr23 = {{5.0}};
        FlatMatrix fm23 = new FlatMatrix(arr23);
        testTestClasses(fm22, boolArr2, fm23, 1, "[true]");
        testTestClasses(fm22, boolArr2, fm23, 3, "[true]");
        double[][] arr24 = {{0.0, 0.0},{3.0, 4.0} };
        FlatMatrix fm24 = new FlatMatrix(arr24);
        boolean[] boolArr3 = {false, true};
        double[][] arr25 = {{1.0, 1.0}, {3.0, 3.0}};
        FlatMatrix fm25 = new FlatMatrix(arr25);
        testTestClasses(fm24, boolArr3, fm25, 1, "[false, true]");
        testTestClasses(null, boolArr1, fm21, 1, "IllegalArgumentException");
        testTestClasses(fm20, null, fm21, 1, "IllegalArgumentException");
        testTestClasses(fm20, boolArr1, null, 1, "IllegalArgumentException");
        testTestClasses(fm20, new boolean[]{true}, fm21, 1, "IllegalArgumentException");
        testTestClasses(fm20, boolArr1, fm21, 0, "IllegalArgumentException");
        testTestClasses(fm20, boolArr1, fm21, 7, "IllegalArgumentException");

        // Test measureAccuracy
        boolean[] boolArr4 = {true, false, true, false};
        boolean[] boolArr5 = {true, false, true, false};
        testMeasureAccuracy(boolArr4, boolArr5, "1.0");
        boolean[] boolArr6 = {true, true};
        boolean[] boolArr7 = {false, false};
        testMeasureAccuracy(boolArr6, boolArr7, "0.0");
        boolean[] boolArr9 = {true, true, false, false};
        boolean[] boolArr0 = {true, false, true, false};
        testMeasureAccuracy(boolArr9, boolArr0, "0.5");
        boolean[] boolArr10 = {true, true, true, false};
        boolean[] boolArr11 = {true, true, true, true};
        testMeasureAccuracy(boolArr10, boolArr11, "0.75");
        boolean[] boolArr12 = {true, true, true, true};
        boolean[] boolArr13 = {true, false, false, false};
        testMeasureAccuracy(boolArr12, boolArr13, "0.25");
        testMeasureAccuracy(null, boolArr5, "IllegalArgumentException");
        testMeasureAccuracy(boolArr4, null, "IllegalArgumentException");
        testMeasureAccuracy(boolArr4, boolArr6, "IllegalArgumentException");


        // Additional tests
        // Students are encouraged to add more test cases to thoroughly test their implementations
    }


    static final String SUCCESS = "\u001B[32mSuccess\u001B[0m";
    static final String FAILED = "\u001B[31mFailed\u001B[0m";

    // Test subMatrix
    public static void testSubMatrix(Matrix mat, int from, int to, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm =  new FlatMatrix(mat);
            Matrix actualOutput = MatrixOperations.subMatrix(fm, from, to);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else  {
                result = FAILED;
            }
            System.out.println("Testing subMatrix: input = \u001B[36m(" + stringify(mat) + ", " + from + ", " + to +
                    ")\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing subMatrix: input = \u001B[36m(" + stringify(mat) + ", " + from + ", " + to +
                        ")\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test normalize
    public static void testNormalize(Matrix mat, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm =  new FlatMatrix(mat);
            Matrix actualOutput = MatrixOperations.normalize(fm);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else  {
                result = FAILED;
            }
            System.out.println("Testing normalize: input = \u001B[36m" + stringify(mat) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing normalize: input = \u001B[36m" + stringify(mat) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test squareDistance
    public static void testSquareDistance(Matrix mat, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm =  new FlatMatrix(mat);
            Matrix actualOutput = MatrixOperations.squareDistance(fm);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing squareDistance: input = \u001B[36m" + stringify(mat) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing squareDIstance: input = \u001B[36m" + stringify(mat) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test multiply
    public static void testMultiply(Matrix mat1, Matrix mat2, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm1 = new FlatMatrix(mat1);
            FlatMatrix fm2 = new FlatMatrix(mat2);
            String actualOutput = stringify(MatrixOperations.multiply(fm1, fm2));
            if (actualOutput.equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing multiply: input = \u001B[36m" + stringify(mat1) + ", " + stringify(mat2) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing multiply: input = \u001B[36m" + stringify(mat1) + ", " +  stringify(mat2) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                result = FAILED;
            }
        }
    }

    // Test diagonal
    public static void testDiagonal(Matrix mat, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm = new FlatMatrix(mat);
            Matrix actualOutput = MatrixOperations.diagonal(fm);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing diagonal: input = \u001B[36m" + stringify(mat) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing diagonal: input = \u001B[36m" + stringify(mat) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test add
    public static void testAdd(Matrix mat1, Matrix mat2, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm1 = new FlatMatrix(mat1);
            FlatMatrix fm2 = new FlatMatrix(mat2);
            Matrix actualOutput = MatrixOperations.add(fm1, fm2);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing add: input = \u001B[36m" + stringify(mat1) + ", " + stringify(mat2) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing add: input = \u001B[36m" + stringify(mat1) + ", " + stringify(mat2) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test sub
    public static void testSub(Matrix mat1, Matrix mat2, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm1 = new FlatMatrix(mat1);
            FlatMatrix fm2 = new FlatMatrix(mat2);
            Matrix actualOutput = MatrixOperations.sub(fm1, fm2);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing sub: input = \u001B[36m" + stringify(mat1) + ", " + stringify(mat2) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing sub: input = \u001B[36m" + stringify(mat1) + ", " + stringify(mat2) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test selectionSort
    public static void testSelectionSort(int[] indices, Comparator<Integer> comp, String expectedOutput) {
        String result;
        try {
            int[] actualOutput = MatrixOperations.selectionSort(indices, comp);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing selectionSort: input = \u001B[36m" + stringify(indices) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing selectionSort: input = \u001B[36m" + stringify(indices) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test testClasses
    public static void testTestClasses(Matrix trainingFeatures, boolean[] trainingClasses, Matrix testFeatures, int k, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm1 = new FlatMatrix(trainingFeatures);
            FlatMatrix fm2 = new FlatMatrix(testFeatures);
            boolean[] actualOutput = MatrixOperations.testClasses(fm1, trainingClasses, fm2, k);
            if (stringify(actualOutput).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing testClasses: Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing testClasses: Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test measureAccuracy
    public static void testMeasureAccuracy(boolean[] testLabels, boolean[] trueLabels, String expectedOutput) {
        String result;
        try {
            String actualOutput = String.valueOf(MatrixOperations.measureAccuracy(testLabels, trueLabels));
            if (actualOutput.equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing measureAccuracy: Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing measureAccuracy: Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }


    private static String stringify(Matrix sm) {
        if (sm == null) return "null";
        String result = "";
        for (int i = 0; i < sm.getNumRows(); i++) {
            result += "(";
            for (int j = 0; j < sm.getNumCols(); j++) {
                result += sm.get(i, j) + " ";
            }
            result = result.substring(0, result.length() - 1) + ")";
        }
        return result;
    }

    private static String stringify(int[] arr) {
        if (arr == null) return "null";
        String result = "[";
        for (int i = 0; i < arr.length; i++) {
            result += arr[i] + ", ";
        }
        result = result.substring(0, result.length() - 2) + "]";
        return result;
    }

    private static String stringify(boolean[] arr) {
        if (arr == null) return "null";
        String result = "[";
        for (int i = 0; i < arr.length; i++) {
            result += arr[i] + ", ";
        }
        result = result.substring(0, result.length() - 2) + "]";
        return result;
    }
}
