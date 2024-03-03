package MathElements;

public class Matrix3x3 {
    public double[][] matrix;

    public Matrix3x3(double [][] matrix){
        if (matrix.length != 3 || matrix[0].length != 3) {
            throw new IllegalArgumentException("Need matrix 3*3");
        }
        this.matrix = matrix;
    }
    public static Point3 times (Matrix3x3 m1, Point3 p1){
        double[] p2 = {0, 0, 0};

        for (int i = 0; i<m1.matrix.length; i++){
            for (int j = 0; j<m1.matrix[0].length; j++){
                if (j==0){
                    p2[i] += m1.matrix[i][j] * p1.x;
                }
                if(j==1){
                    p2[i] += m1.matrix[i][j] * p1.y;
                }
                if(j==2){
                    p2[i] += m1.matrix[i][j] * p1.z;
                }
            }
        }
        return new Point3(p2[0],p2[1],p2[2]);
    }

    public static Matrix3x3 times (Matrix3x3 m1, Matrix3x3 m2) {
        double[][] result = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                }
            }
        }
        return new Matrix3x3(result);
    }

    public static Matrix3x3 identity() {
        return new Matrix3x3(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }

    public static Matrix3x3 translation(double tx, double ty) {
        return new Matrix3x3(new double[][]{
                {1, 0, tx},
                {0, 1, ty},
                {0, 0, 1}
        });
    }
    public static Matrix3x3 rotation(double degree) {
        degree = Math.toRadians(degree);
        return new Matrix3x3(new double[][]{
                {Math.cos(degree),  Math.sin((-1)*degree),0},
                {Math.sin(degree), Math.cos(degree), 0},
                {0, 0, 1}
        });
    }
    public static Matrix3x3 scaling(double sx, double sy){
        return new Matrix3x3(new double[][]{
                {sx, 0, 0},
                {0, sy, 0},
                {0, 0, 1}
        });
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
