
public class MatrixInverse {

	public static double[][] findInverse(double[][] matrix) throws Exception {
		if(determinant(matrix)==0) {
        	throw new Exception("Inverse of given matrix can not be calculated!");
		}
		
		int n = matrix.length;
		double[][] inverse = new double[n][n];
		double[][] augmented = new double[n][2 * n];

		// Initialize the augmented matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				augmented[i][j] = matrix[i][j];
			}
			augmented[i][i + n] = 1;
		}

		// Perform row operations to get the identity matrix on the left side of the
		// augmented matrix
		for (int i = 0; i < n; i++) {
			// Divide the row by the diagonal element
			double temp = augmented[i][i];
			for (int j = 0; j < 2 * n; j++) {
				augmented[i][j] /= temp;
			}

			// Subtract the row from other rows to get the zeros below the diagonal element
			for (int j = 0; j < n; j++) {
				if (i != j) {
					double factor = augmented[j][i];
					for (int k = 0; k < 2 * n; k++) {
						augmented[j][k] -= factor * augmented[i][k];
					}
				}
			}
		}

		// Copy the inverse matrix from the augmented matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				inverse[i][j] = augmented[i][j + n];
			}
		}

		return inverse;
	}

	public static double determinant(double[][] matrix) {
		int n = matrix.length;
		double det = 0;
		if (n == 1) {
			det = matrix[0][0];
		} else if (n == 2) {
			det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
		} else {
			for (int j1 = 0; j1 < n; j1++) {
				double[][] submatrix = new double[n - 1][];
				for (int k = 0; k < n - 1; k++) {
					submatrix[k] = new double[n - 1];
				}
				for (int i = 1; i < n; i++) {
					int j2 = 0;
					for (int j = 0; j < n; j++) {
						if (j == j1) {
							continue;
						}
						submatrix[i - 1][j2] = matrix[i][j];
						j2++;
					}
				}
				det += Math.pow(-1.0, j1 + 2.0) * matrix[0][j1] * determinant(submatrix);
			}
		}
		return det;
	}

}
