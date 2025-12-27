
import java.util.Comparator;

// Task 1.4
public class IndexComparator implements Comparator<Integer> {

    double[] array;

    // Assumes: 'm' is not null and that 'row' is a valid row index in 'm'
    public IndexComparator(Matrix m, int row){
        // ---------------write your code BELOW this line only! ------------------
        if ((m == null) || (row < 0) || (row >= m.getNumRows()))
            throw new IllegalArgumentException("Invalid input");
        this.array = new double[m.getNumCols()];
        for (int j = 0; j < m.getNumCols(); j++) {
            this.array[j] = m.get(row, j);
        }
        // ---------------write your code ABOVE this line only! ------------------
    }


    // Assumes: 'idx1' and 'idx2' are legal indices for the row in Matrix
    public int compare(Integer idx1, Integer idx2) {
        int ans = 0;
        // ---------------write your code BELOW this line only! ------------------
        if ((idx1 < 0) || (idx2 < 0) || (idx1 >= this.array.length) || (idx2 >= this.array.length))
            throw new IllegalArgumentException("Invalid indices");
        if (this.array[idx1] < this.array[idx2]) {
            ans = -1;
        }
        else if (this.array[idx1] > this.array[idx2]) {
            ans = 1;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
}
