public class Main {
    public static void main(String[] args) {
        double[][] arr = {{4,2,3}, {4,2,6}, {5,6,2}};
        Matrix mat = new SimpleMatrix(arr);
        System.out.println(mat);
        System.out.println(MatrixOperations.subMatrix(mat, 1, 3));
    }
}
