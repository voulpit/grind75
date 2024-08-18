package grind75;

// 01 Matrix
// https://leetcode.com/problems/01-matrix/

public class UpdateMatrixDistance {

    public int[][] updateMatrix(int[][] mat) {
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[0].length; j++) {
                // set distance 0
                mat[i][j] = mat[i][j] == 0 ? 0 : Integer.MAX_VALUE;
            }
        }

        // set distance 1
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    if (i > 0 && mat[i-1][j] > 1) {
                        mat[i-1][j] = 1;
                    }
                    if (i < mat.length-1 && mat[i+1][j] > 1) {
                        mat[i+1][j] = 1;
                    }
                    if (j > 0 && mat[i][j-1] > 1) {
                        mat[i][j-1] = 1;
                    }
                    if (j < mat[0].length-1 && mat[i][j+1] > 1) {
                        mat[i][j+1] = 1;
                    }
                }
            }
        }

        // set further distances
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    if (i > 0 && mat[i-1][j] > 2) {
                        setDistance(i-1, j, 2, mat);
                    }
                    if (i < mat.length-1 && mat[i+1][j] > 2) {
                        setDistance(i+1, j, 2, mat);
                    }
                    if (j > 0 && mat[i][j-1] > 2) {
                        setDistance(i, j-1, 2, mat);
                    }
                    if (j < mat[0].length-1 && mat[i][j+1] > 2) {
                        setDistance(i, j+1, 2, mat);
                    }
                }
            }
        }

        return mat;
    }

    private void setDistance(int i, int j, int val, int mat[][]) {
        mat[i][j] = val;
        if (i > 0 && mat[i-1][j] > val+1) {
            setDistance(i-1, j, val+1, mat);
        }
        if (i < mat.length-1 && mat[i+1][j] > val+1) {
            setDistance(i+1, j, val+1, mat);
        }
        if (j > 0 && mat[i][j-1] > val+1) {
            setDistance(i, j-1, val+1, mat);
        }
        if (j < mat[0].length-1 && mat[i][j+1] > val+1) {
            setDistance(i, j+1, val+1, mat);
        }
    }
}
