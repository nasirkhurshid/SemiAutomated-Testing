import java.util.Arrays;

public class MatrixInverseTester {
    public static void main(String[] args) {
        double[][] A = {
        		{1, 1, 1}, 
        		{1, 1, 1}, 
        		{1, 1, 1}
        		};
        
//        double A[][] = {
//        		{1, 2, 3},
//        		{4, 5, 6},
//        		{7, 8, 9}
//        		};
        
        double[][] I = {
        		  {1, 0, 0},
        		  {0, 1, 0},
        		  {0, 0, 1},
        		};

        double[][] A_Inverse;
		try {
			A_Inverse = MatrixInverse.findInverse(A);

//			 matrix * I
	        double[][] product = multiplyMatrices(A, A_Inverse);

//	         Compare the product with the identity matrix I
	        if (deepEquals(product, I)) {
	            System.out.println("PASS: A X A-1 = I: "+Arrays.deepToString(A_Inverse));
	        } else {
	            System.out.println("FAIL: A X A-1 != I");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public static double[][] multiplyMatrices(double[][] A, double[][] B) {
        int n = A.length;
        int m = B[0].length;
        double[][] C = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;
                for (int k = 0; k < A[0].length; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }
    
	public static boolean deepEquals(double[][] A, double[][] B) {
		if (A.length != B.length || A[0].length != B[0].length) {
			return false;
		}
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (Math.abs(A[i][j] - B[i][j]) >= Math.pow(10, -5)) {
					return false;
				}
			}
		}
		return true;
	}

}
