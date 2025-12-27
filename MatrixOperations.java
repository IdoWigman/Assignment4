
import java.util.Comparator;

// Task 1
public class MatrixOperations {

    // Task 1.1
    // Assumes: 'm' not null and from and to legal indices
    // Assumes: from < to
    // Returns: FlatMatrix starting from row 'from' to row 'to'
    public static Matrix subMatrix(Matrix mat, int from, int to) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        ans = new FlatMatrix(to - from, mat.getNumCols());
        for (int i = 0; i < ans.getNumRows(); i++) {
            for (int j = 0; j < ans.getNumCols(); j++) {
                ans.set(i, j, mat.get(i + from, j));
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.2
    // Assumes: 'm' not null
    // Returns: the normalized matrix
    public static Matrix normalize(Matrix m) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if ((m == null) || (m.getNumRows() == 0))
            throw new IllegalArgumentException("Matrix can't be null");
        ans = new FlatMatrix(m.getNumRows(), m.getNumCols());
        for (int j = 0; j < m.getNumCols(); j++) {
            double mean = calMean(m, j); // call for helper function
            double std = calStd(m, j, mean); // call for helper function
            if (std != 0)
                for (int i = 0; i < m.getNumRows(); i++) {
                    double current = m.get(i, j);
                    ans.set(i, j, (current - mean) / std);
                }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // helper for task 1.2
    // Assumes: 'm' not null and 'col' is a valid index for a column in 'm'
    // Return: the mean for the specified column in the matrix 'm'
    public static double calMean(Matrix m, int col) {
        double sum = 0.0;
        for (int i = 0; i < m.getNumRows(); i++) {
            sum += m.get(i, col);
        }
        return sum / m.getNumRows();
    }

    // helper for task 1.2
    // Assumes: 'm' not null, 'col' is a valid index for a column in 'm'
    // Return: the deviation for the specified column in the matrix 'm'
    public static double calStd(Matrix m, int col, double mean) {
        double sum = 0.0;
        for (int i = 0; i < m.getNumRows(); i++) {
            double current = m.get(i, col);
            sum += (mean - current) * (mean - current);
        }
        return Math.sqrt(sum / m.getNumRows());
    }

    // Task 1.3
    // Assumes: 'm' is not null
    // Returns: the square distance matrix of 'mat'
    public static Matrix squareDistance(Matrix m) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }



    // Assumes: 'mat1' and 'mat2' be able to multiply
    // Returns: the product of 'mat1' and 'mat2'
    public static Matrix multiply(Matrix mat1, Matrix mat2) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }


    // Task 1.3A
    // Assumes: 'mat' not null
    // Returns: the diagonal matrix
    public static Matrix diagonal(Matrix m) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if((m == null) || (!m.isSquare()))
            throw new IllegalArgumentException("Matrix is invalid");
        ans = new FlatMatrix(m.getNumRows(), m.getNumCols());
        for (int i = 0; i < m.getNumRows(); i++) {
            ans.set(i, i, m.get(i, i));
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.3B
    // Assumes: 'm1' and 'm2' be able to add
    // Returns: the sum of 'mat1' and 'mat2'
    public static Matrix add(Matrix m1, Matrix m2) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if((m1 == null) || (m2 == null) || (m1.getNumRows() != m2.getNumRows()) || (m1.getNumCols() != m2.getNumCols()))
            throw new IllegalArgumentException("Input is invalid");
        ans = new FlatMatrix(m1.getNumRows(), m1.getNumCols());
        for (int i = 0; i < m1.getNumRows(); i++) {
            for (int j = 0; j < m1.getNumCols(); j++) {
                ans.set(i, j, m1.get(i, j) + m2.get(i, j));
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.3C
    // Assumes: 'm1' and 'm2' be able to subtract
    // Returns: the subtraction of 'mat1' and 'mat2'
    public static Matrix sub(Matrix m1, Matrix m2) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.4
    // Assumes: 'indices' is an index array
    // returns the sorted indexes of indices based on comp
    public static int[] selectionSort(int[] array, Comparator<Integer> comp) {
        int[] ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.5
    // Assumes: 'trainingFeatures', trainingLabels', 'validationFeatures' npt be null
    // Assumes: compatibility between 'trainingFeatures' and 'trainingLabels'
    // Returns: the validation labels of the 'validationFeatures'
    public static boolean[] testClasses(Matrix trainingFeatures, boolean[] trainingClasses, Matrix testFeatures, int k) {
        boolean[] ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.6
    // Assumes: 'testLabels' and 'trueLables' are arrays of same length
    // Returns: the accuracy of the test
    public static double measureAccuracy(boolean[] testLabels, boolean[] trueLabels) {
        double ans = 0;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }


}
