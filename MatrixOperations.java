
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
            throw new IllegalArgumentException("Invalid input");
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
        MatrixUtils.roundMatrix(ans);
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
        if (m == null)
            throw new IllegalArgumentException("Matrix can't be null");
        int dim = m.getNumRows();
        Matrix ones = new FlatMatrix(dim, dim);
        for (int i =0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                ones.set(i, j, 1.0);
            }
        }
        Matrix fT = m.transpose(); // fT is the transpose of m
        Matrix mult = multiply(m, fT); // mult is the product of m times fT
        Matrix diag = diagonal(mult); // diag is the diagonal of mult (where all the values are '0' other than the diagonal)
        Matrix firstTerm = multiply(ones, diag);
        Matrix secondTerm = multiply(diag, ones);
        Matrix thirdTerm = add(mult, mult);
        ans = sub(add(firstTerm, secondTerm), thirdTerm);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }



    // Assumes: 'mat1' and 'mat2' be able to multiply
    // Returns: the product of 'mat1' and 'mat2'
    public static Matrix multiply(Matrix mat1, Matrix mat2) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if ((mat1 == null) || (mat2 == null) || (mat1.getNumCols() != mat2.getNumRows()))
            throw new IllegalArgumentException("Invalid input");
        ans = new FlatMatrix(mat1.getNumRows(), mat2.getNumCols());
        for (int i = 0; i < ans.getNumRows(); i++) {
            for (int j = 0; j < ans.getNumCols(); j++) {
                for (int k = 0; k < mat1.getNumCols(); k++) {
                    double mult = mat1.get(i, k) * mat2.get(k, j);
                    ans.set(i, j, ans.get(i, j) + mult);
                }
            }
        }
        MatrixUtils.roundMatrix(ans);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }


    // Task 1.3A
    // Assumes: 'mat' not null
    // Returns: the diagonal matrix
    public static Matrix diagonal(Matrix m) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if ((m == null) || (!m.isSquare()))
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
        if ((m1 == null) || (m2 == null) || (m1.getNumRows() != m2.getNumRows()) || (m1.getNumCols() != m2.getNumCols()))
            throw new IllegalArgumentException("Input is invalid");
        MatrixUtils.roundMatrix(m1);
        MatrixUtils.roundMatrix(m2);
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
        if ((m1 == null) || (m2 == null) || (m1.getNumRows() != m2.getNumRows()) || (m1.getNumCols() != m2.getNumCols()))
            throw new IllegalArgumentException("Input is invalid");
        MatrixUtils.roundMatrix(m1);
        MatrixUtils.roundMatrix(m2);
        ans = new FlatMatrix(m1.getNumRows(), m1.getNumCols());
        for (int i = 0; i < m1.getNumRows(); i++) {
            for (int j = 0; j < m1.getNumCols(); j++) {
                ans.set(i, j, Math.abs(m1.get(i, j) - m2.get(i, j)));
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.4
    // Assumes: 'indices' is an index array
    // returns the sorted indexes of indices based on comp
    public static int[] selectionSort(int[] array, Comparator<Integer> comp) {
        int[] ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if ((array == null) || (comp == null))
            throw new IllegalArgumentException("Input is invalid");
        ans = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            ans[i] = array[i];
        }
        for (int i = 0; i < ans.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < ans.length; j++) {
                if (comp.compare(ans[j], ans[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            int temp = ans[minIndex];
            ans[minIndex] = ans[i];
            ans[i] = temp;
        }
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
        if ((trainingFeatures == null) || (trainingClasses == null) || (testFeatures == null))
            throw new IllegalArgumentException("Inputs can't be null");
        if ((trainingFeatures.getNumCols() != testFeatures.getNumCols()) || (trainingFeatures.getNumRows() != trainingClasses.length))
            throw new IllegalArgumentException("Input is invalid");
        if ((k < 1) || (k > trainingFeatures.getNumRows()))
            throw new IllegalArgumentException("k is invalid");
        ans = new boolean[testFeatures.getNumRows()];
        Matrix normTrainFeat = normalize(trainingFeatures);
        Matrix normTestFeat = normalize(testFeatures, trainingFeatures); // call for helper function
        Matrix allFeatures = concatenate(normTrainFeat, normTestFeat); // call for helper function
        Matrix squareDistance = squareDistance(allFeatures);
        int[] kNearest = new int[k];
        for (int i = 0; i < normTestFeat.getNumRows(); i++) {
            IndexComparator testRowComp =  new IndexComparator(squareDistance, i + normTrainFeat.getNumRows());
            int[] allIndices = indexArray(trainingFeatures.getNumRows());
            allIndices = selectionSort(allIndices, testRowComp); // sorts the indices in 'allIndices'
            for (int j = 0; j < k; j++) {
                kNearest[j] = allIndices[j];
            }
            int positive = 0;
            int negative = 0;
            for (int j = 0; j < k; j++) {
                if (trainingClasses[kNearest[j]])
                    positive++;
                else
                    negative++;
            }
            ans[i] = positive > negative;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Helper for task 1.5
    // Assumes: 'primary' and 'base' not null, both have matching number of columns and have at least one row
    // Returns: the normalized matrix of the 'primary' matrix
    public static Matrix normalize(Matrix primary, Matrix base) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if ((primary == null) || (base == null) || (primary.getNumRows() == 0) || (base.getNumRows() == 0))
            throw new IllegalArgumentException("Invalid input");
        if (primary.getNumCols() != base.getNumCols())
            throw new IllegalArgumentException("Number of columns doesn't match");
        ans = new FlatMatrix(primary.getNumRows(), primary.getNumCols());
        for (int j = 0; j < base.getNumCols(); j++) {
            double mean = calMean(base, j);
            double std = calStd(base, j, mean);
            if (std != 0)
                for (int i = 0; i < primary.getNumRows(); i++) {
                    double current = primary.get(i, j);
                    ans.set(i, j, (current - mean) / std);
                }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Helper for task 1.5
    // Assumes: 'top' and 'bottom' not null and both have matching number of columns
    // Returns: the concatenated matrix that has 'top' on the top and 'bottom' at the bottom
    public static Matrix concatenate(Matrix top, Matrix bottom) {
        if ((top == null) || (bottom == null) || (top.getNumCols() != bottom.getNumCols()))
            throw new IllegalArgumentException("Input is invalid");
        Matrix allFeatures = new FlatMatrix(top.getNumRows() + bottom.getNumRows(), top.getNumCols());
        for (int j = 0; j < top.getNumCols(); j++) {
            for (int i = 0; i < top.getNumRows(); i++) {
                allFeatures.set(i, j, top.get(i, j));
            }
        }
        for (int j = 0; j < bottom.getNumCols(); j++) {
            for (int i = top.getNumRows(); i < bottom.getNumRows() + top.getNumRows(); i++) {
                allFeatures.set(i, j, bottom.get(i - top.getNumRows(), j));
            }
        }
        return allFeatures;
    }

    // Helper for task 1.5
    // Assumes: n >= 0
    // Returns: An array with n elements and each element is the same as its index
    public static int[] indexArray(int n) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = i;
        return ans;
    }

    // Task 1.6
    // Assumes: 'testLabels' and 'trueLables' are arrays of same length
    // Returns: the accuracy of the test
    public static double measureAccuracy(boolean[] testLabels, boolean[] trueLabels) {
        double ans = 0;
        // ---------------write your code BELOW this line only! ------------------
        double sum = 0.0;
        for (int i = 0; i < testLabels.length; i++) {
            if (testLabels[i] == trueLabels[i])
                sum++;
        }
        ans = sum / testLabels.length;
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }


}
