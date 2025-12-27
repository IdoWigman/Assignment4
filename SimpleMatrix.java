
/// **********************DELETE AFTER*************************** ///
public class SimpleMatrix implements Matrix {

    private int rows;
    private int cols;
    private double[][] mat;

	// Task 3.1
    // 'rows' > 0, 'cols' > 0 
    // Constructs a SimpleMatrix with the given rows and cols, with zeros in all cells
    public SimpleMatrix(int rows, int cols) {
        // ---------------write your code BELOW this line only! ------------------
        if ((rows <= 0) || (cols <= 0))
            throw new IllegalArgumentException("rows and cols must be positive");
        this.rows = rows;
        this.cols = cols;
        mat = new double[rows][cols];
        // ---------------write your code ABOVE this line only! ------------------
    }

	// Task 3.2
    // 'arr' is not null or empty
    // Constructs a SimpleMatrix with the values of 'arr'
    public SimpleMatrix(double[][] arr) {
        // ---------------write your code BELOW this line only! ------------------
        this.rows = arr.length;
        this.cols = arr[0].length;
        this.mat = new double[rows][cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                mat[i][j] = arr[i][j];
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

	// Task 3.3
    // 'mat' is not null
    // Constructs a SimpleMatrix with the values of 'mat'
    public SimpleMatrix(Matrix mat) {
        // ---------------write your code BELOW this line only! ------------------
        if (mat == null)
            throw new IllegalArgumentException("mat cannot be null");
        this.rows = mat.getNumRows();
        this.cols = mat.getNumCols();
        this.mat = new double[rows][cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.mat[i][j] = mat.get(i, j);
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

    // Task 3.4
    // 'row' and 'col' are legal indices in the matrix
    // Returns the value in the corresponding row and col in the matrix
    public double get(int row, int col) {
		double ans = 0;
		// ---------------write your code BELOW this line only! ------------------
        if ((row < 0) || (row >= this.rows) || (col < 0) || (col >= this.cols))
            throw new IllegalArgumentException("row/collum not in range");
        ans = mat[row][col];
        // ---------------write your code ABOVE this line only! ------------------
		return ans;
    }

    // Task 3.5
    // 'row' and 'col' are legal indices in the matrix
    // Updates the value in the corresponding row and col in the matrix to be 'value'
    public void set(int row, int col, double value) {
        // ---------------write your code BELOW this line only! ------------------
        if ((row < 0) || (row >= this.rows) || (col < 0) || (col >= this.cols))
            throw new IllegalArgumentException("row/collum not in range");
        mat[row][col] = value;
        // ---------------write your code ABOVE this line only! ------------------
    }

	// Task 3.6
    // Returns the transposed matrix
    public Matrix transpose() {
        SimpleMatrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        ans = new SimpleMatrix(this.cols, this.rows);
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                ans.set(i, j, this.get(j, i));
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

    public Boolean isSquare() {
        return rows == cols;
    }

    public Matrix copy() {
        return new SimpleMatrix(this);
    }

	//Task 3.7
    public String toString() {
        String s = "";
        // ---------------write your code BELOW this line only! ------------------
        s += "SimpleMatrix " + this.rows + " X " + this.cols;
        // ---------------write your code ABOVE this line only! ------------------
        return s;
    }

	//Task 3.8
	public boolean equals(Object other) {
        boolean ans = false;
        // ---------------write your code BELOW this line only! ------------------
        if (other instanceof Matrix) {
            Matrix matOther = (Matrix)other;
            if ((matOther.getNumRows() == this.rows) && (matOther.getNumCols() == this.cols)) {
                ans = true;
                for (int i = 0; (i < this.rows) && (ans); i++) {
                    for (int j = 0; (j < this.cols) && (ans); j++) {
                        ans = matOther.get(i, j) == this.get(i, j);
                    }
                }
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

}
