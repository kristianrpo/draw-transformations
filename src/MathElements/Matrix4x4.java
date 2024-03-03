package MathElements;

public class Matrix4x4 {
    public double[][] matrix;

    public Matrix4x4(double [][] matrix){
        if (matrix.length != 4 || matrix[0].length != 4) {
            throw new IllegalArgumentException("Need matrix 4*4");
        }
        this.matrix = matrix;
    }
    public static Point4 times (Matrix4x4 m1, Point4 p1){
        double[] p2 = {0, 0, 0, 0};

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
                if(j==3){
                    p2[i] += m1.matrix[i][j] * p1.w;
                }
            }
        }
        return new Point4(p2[0],p2[1],p2[2],p2[3]);
    }

    public static Matrix4x4 times (Matrix4x4 m1, Matrix4x4 m2) {
        double[][] result = new double[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                }
            }
        }
        return new Matrix4x4(result);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
