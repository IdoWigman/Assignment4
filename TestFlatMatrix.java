public class TestFlatMatrix {
    public static void main(String[] args) {

        // Test constructor with rows and cols
        testConstructorWithRowsCols(0, 0, "IllegalArgumentException");
        testConstructorWithRowsCols(0, 1, "IllegalArgumentException");
        testConstructorWithRowsCols(1, 0, "IllegalArgumentException");
        testConstructorWithRowsCols(2, 2, "(0.0 0.0)(0.0 0.0)");

        // Test constructor with matrix
        FlatMatrix fm1 = new FlatMatrix(3, 2);
        testConstructorWithMatrix(null, "IllegalArgumentException");
        testConstructorWithMatrix(fm1, "(0.0 0.0)(0.0 0.0)(0.0 0.0)");

        // Test constructor with 2D array
        double[][] arr1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        testConstructorWith2DArray(null, "IllegalArgumentException");
        testConstructorWith2DArray(arr1, "(1.0 2.0 3.0)(4.0 5.0 6.0)(7.0 8.0 9.0)");

        // Test get
        double[][] arr2 = {{0, 1, 2}, {10, 11, 12}, {20, 21, 22}};
        FlatMatrix fm2 = new FlatMatrix(arr2);
        testGet(fm2, -1, 2, "IllegalArgumentException");
        testGet(fm2, 1, -2, "IllegalArgumentException");
        testGet(fm2, 3, 2, "IllegalArgumentException");
        testGet(fm2, 1, 3, "IllegalArgumentException");
        testGet(fm2, 1, 2, "12.0");

        // Test set
        double[][] arr3 = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        FlatMatrix fm3 = new FlatMatrix(arr3);
        testSet(fm3, -1, 2, 2, true);
        testSet(fm3, 1, -2, 2, true);
        testSet(fm3, 6, 2, 2, true);
        testSet(fm3, 1, 4, 2, true);
        testSet(fm3, 1, 2, 2, false);

        // Test getNumRows
        FlatMatrix fm4 = new FlatMatrix(3, 2);
        testGetNumRows(fm4, 3);

        // Test getNumCols
        FlatMatrix fm5 = new FlatMatrix(3, 2);
        testGetNumCols(fm4, 2);

        // Test isSquare
        FlatMatrix fm6 = new FlatMatrix(3, 2);
        testIsSquare(fm6, false);
        FlatMatrix fm7 = new FlatMatrix(2, 2);
        testIsSquare(fm7, true);

        // Test copy
        double[][] arr4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        FlatMatrix fm8 = new FlatMatrix(arr4);
        testCopy(fm8, "(1.0 2.0 3.0)(4.0 5.0 6.0)(7.0 8.0 9.0)");

        // Test transpose
        double[][] arr5 = {{3.2, 4.67, 0.3}, {13.76, 22.0, 1.0}};
        FlatMatrix fm9 = new FlatMatrix(arr5);
        testTranspose(fm9, "(3.2 13.76)(4.67 22.0)(0.3 1.0)");

        // Test equals
        double[][] arr6 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        FlatMatrix fm10 = new FlatMatrix(arr6);
        double[][] arr7 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        FlatMatrix fm11 = new FlatMatrix(arr7);
        testEquals(fm10, fm11, true);
        double[][] arr8 = {{1, 2, 3}, {5, 4, 6}, {7, 8, 9}};
        FlatMatrix fm12 = new FlatMatrix(arr8);
        testEquals(fm11, fm12, false);
        testEquals(fm12, arr8, false);

        // Test toString
        FlatMatrix fm13 = new FlatMatrix(4, 1);
        testToString(fm13, "FlatMatrix 4 X 1");

        // Additional tests
        // Students are encouraged to add more test cases to thoroughly test their implementations
    }


    static final String SUCCESS = "\u001B[32mSuccess\u001B[0m";
    static final String FAILED = "\u001B[31mFailed\u001B[0m";

    // Test constructor with rows and cols
    public static void testConstructorWithRowsCols(int inputRows, int inputCols, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm = new FlatMatrix(inputRows, inputCols);
            if (stringify(fm).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing constructor with rows and cols: input = \u001B[36m(" + inputRows + ", " + inputCols +
                    ")\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(fm) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing constructor with rows and cols: input = \u001B[36m(" + inputRows + ", " + inputCols +
                        ")\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test constructor with rows and cols
    public static void testConstructorWithMatrix(Matrix mat, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm = new FlatMatrix(mat);
            if (stringify(fm).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing constructor with matrix: input = \u001B[36m" + stringify(mat) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(fm) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing constructor with matrix: input = \u001B[36m" + stringify(mat) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test constructor with rows and cols
    public static void testConstructorWith2DArray(double[][] arr, String expectedOutput) {
        String result;
        try {
            FlatMatrix fm = new FlatMatrix(arr);
            if (stringify(fm).equals(expectedOutput)) {
                result = SUCCESS;
            } else {
                result = FAILED;
            }
            System.out.println("Testing constructor with matrix: input = \u001B[36m" + stringify(arr) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(fm) + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing constructor with matrix: input = \u001B[36m" + stringify(arr) +
                        "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test get
    public static void testGet(FlatMatrix mat, int row, int col, String expectedOutput) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        try {
            String actualOutput = String.valueOf(fm.get(row, col));
            if (actualOutput.equals(expectedOutput)) {
                result = SUCCESS;
            }  else {
                result = FAILED;
            }
            System.out.println("Testing get: input = \u001B[36m(" + row + ", " + col +
                    ")\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (expectedOutput.equals("IllegalArgumentException")) {
                result = SUCCESS;
                System.out.println("Testing get: input = \u001B[36m(" + row + ", " + col +
                        ")\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test set
    public static void testSet(FlatMatrix mat, int row, int col, double value, boolean shouldThrowException) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        try {
            fm.set(row, col, value);
            double actualOutput = fm.get(row, col);
            if (actualOutput == value) {
                result = SUCCESS;
            } else  {
                result = FAILED;
            }
            System.out.println("Testing set: input = \u001B[36m(" + row + ", " + col + ", " + value +
                    ")\u001B[0m; Expected output = \u001B[36m" + value + "\u001B[0m; Actual Output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
        } catch (IllegalArgumentException e) {
            if (shouldThrowException) {
                result = SUCCESS;
                System.out.println("Testing set: input = \u001B[36m(" + row + ", " + col + ", " + value +
                        ")\u001B[0m; Expected output = \u001B[36mIllegalArgumentException\u001B[0m; --> " + result);
            } else {
                throw e;
            }
        }
    }

    // Test getNumRows
    public static void testGetNumRows(FlatMatrix mat,int expectedValue) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        int actualOutput = fm.getNumRows();
        if (actualOutput == expectedValue) {
            result = SUCCESS;
        } else  {
            result = FAILED;
        }
        System.out.println("Testing getNumRows: input = \u001B[36m" + stringify(mat) +
                "\u001B[0m; Expected output = \u001B[36m" + expectedValue + "\u001B[0m; Actual Output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
    }

    // Test getNumCols
    public static void testGetNumCols(FlatMatrix mat,int expectedValue) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        int actualOutput = fm.getNumCols();
        if (actualOutput == expectedValue) {
            result = SUCCESS;
        } else  {
            result = FAILED;
        }
        System.out.println("Testing getNumCols: input = \u001B[36m" + stringify(mat) +
                "\u001B[0m; Expected output = \u001B[36m" + expectedValue + "\u001B[0m; Actual Output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
    }

    // Test isSquare
    public static void testIsSquare(FlatMatrix mat,boolean expectedValue) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        boolean actualOutput = fm.isSquare();
        if (actualOutput == expectedValue) {
            result = SUCCESS;
        } else {
            result = FAILED;
        }
        System.out.println("Testing isSquare: input = \u001B[36m" + stringify(mat) +
                "\u001B[0m; Expected output = \u001B[36m" + expectedValue + "\u001B[0m; Actual Output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
    }

    // Test copy
    public static void testCopy(FlatMatrix mat, String expectedOutput) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        Matrix actualOutput = fm.copy();
        if (stringify(actualOutput).equals(expectedOutput)) {
            result = SUCCESS;
        } else {
            result = FAILED;
        }
        System.out.println("Testing copy: input = \u001B[36m" + stringify(mat) +
                "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
    }

    // Test transpose
    public static void testTranspose(FlatMatrix mat, String expectedOutput) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        Matrix actualOutput = fm.transpose();
        if (stringify(actualOutput).equals(expectedOutput)) {
            result = SUCCESS;
        } else {
            result = FAILED;
        }
        System.out.println("Testing transpose: input = \u001B[36m" + stringify(mat) +
                "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual Output = \u001B[36m" + stringify(actualOutput) + "\u001B[0m; --> " + result);
    }

    // Test equals
    public static void testEquals(FlatMatrix mat, Object testObj, boolean expectedOutput) {
        String result;
        FlatMatrix fm1 = new FlatMatrix(mat);
        boolean actualOutput =  fm1.equals(testObj);
        if (actualOutput == expectedOutput) {
            result = SUCCESS;
        } else {
            result = FAILED;
        }
        if (testObj instanceof FlatMatrix) {
            System.out.println("Testing equals: input = \u001B[36m" + stringify(mat) + ", " + stringify((FlatMatrix)testObj) +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
        } else {
            System.out.println("Testing equals: input = \u001B[36m" + stringify(mat) + ", " + testObj +
                    "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
        }
    }

    // Test toString
    public static void testToString(FlatMatrix mat, String expectedOutput) {
        String result;
        FlatMatrix fm = new FlatMatrix(mat);
        String actualOutput =  fm.toString();
        if (actualOutput.equals(expectedOutput)) {
            result = SUCCESS;
        } else {
            result = FAILED;
        }
        System.out.println("Testing toString: input = \u001B[36m" + stringify(mat) +
                "\u001B[0m; Expected output = \u001B[36m" + expectedOutput + "\u001B[0m; Actual output = \u001B[36m" + actualOutput + "\u001B[0m; --> " + result);
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

    private static String stringify(double[][] arr) {
        if (arr == null) return "null";
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            result += "[";
            for (int j = 0; j < arr[i].length; j++) {
                result += arr[i][j] + ", ";
            }
            result = result.substring(0, result.length() - 2) + "]";
        }
        return result;
    }
}
