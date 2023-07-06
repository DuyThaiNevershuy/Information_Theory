public class Ex1 {
    public static double log2(double digit) {
        return Math.log(digit) / Math.log(2);
    }

    public static double calculateHX(double[][] matrix) {
        int rows = matrix.length;
        int col = matrix[0].length;
        double entropyX = 0;
        for (int j = 0; j < col; j++) {
            double colSum = 0;
            for (int i = 0; i < rows; i++) {
                colSum += matrix[i][j];
            }
            entropyX += colSum * log2(colSum);
        }
        return entropyX * (-1);
    }

    public static double calculateHY(double[][] matrix) { //HY thì thuận for i j ->> matrix[i][j] HX thì for [i][j] matrix [j][i]
        int rows = matrix.length;
        int col = matrix[0].length;
        double entropyY = 0;
        for (int i = 0; i < rows; i++) {
            double rowSum = 0;
            for (int j = 0; j < col; j++) {
                rowSum += matrix[i][j];
            }
            entropyY += rowSum * log2(rowSum);
        }
        return entropyY * (-1);
    }

    public static double calculateX_Given_Y(double[][] matrix) {
        int rows = matrix.length;
        int col = matrix[0].length;
        double H_XGivenY = 0;
        for (int i = 0; i < rows; i++) {
            double rowSum = 0;
            for (int j = 0; j < col; j++) {
                rowSum += matrix[i][j];
            }
            //Dùng công thức P(X,Y) = P(Y) * P(X|Y)
            double temp = 0;
            double ProbabilityOfXGivenY;
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] > 0) {
                    ProbabilityOfXGivenY = matrix[i][j] / rowSum; //P(X|Y) = P(X,Y) * P(Y) (PY = rowSum)
                    temp -= (ProbabilityOfXGivenY) * log2(ProbabilityOfXGivenY);
                    //Cả biểu thức này bằng: temp = P(X|Y) * log2(P_X(X|Y))
                    //Suy ra để tính H(X|Y) cần nhân thêm P(X,Y) = rowSum
                }
            }
            H_XGivenY += rowSum * temp; //H(X|Y) = P(X,Y) * P(X|Y) * log2(P_X(X|Y))
        }
        return H_XGivenY;
    }


    public static void main(String[] args) {
        double[][] matrix_test = {
                {0.0625, 0.375, 0.0625},
                {0.0625, 0.1875, 0},
                {0, 0.1875, 0.0625}
        };
        System.out.println("HX: " + calculateHX(matrix_test));
        System.out.println("HY: " + calculateHY(matrix_test));
        System.out.println("H(X|Y): " + calculateX_Given_Y(matrix_test));

    }


}
