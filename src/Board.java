public class Board {

    public static Boolean notInAllCorners(int row, int column, int n) { // row and column can be 0,1,2...

        if (notInUpCorner(row, n) && notInDownCorner(row, n) && notInLeftCorner(column, n)
                && notInRightcorner(column, n)) {
            return true;
        } else
            return false;

        /*
         * if (column == 0 && (column + 1) == n && row == 0 && (row + 1) == n) {
         * return false;
         * }
         */
    }

    public static Boolean notInLeftCorner(int column, int n) {
        if (column == 0) {
            return false;
        }
        return true;
    }

    public static Boolean notInRightcorner(int column, int n) {
        if ((column + 1) == n) {
            return false;
        }
        return true;
    }

    public static Boolean notInUpCorner(int row, int n) {
        if (row == 0) {
            return false;
        }
        return true;
    }

    public static Boolean notInDownCorner(int row, int n) {
        if ((row + 1) == n) {
            return false;
        }
        return true;
    }

    public static void drawBoard(int n, String board[][]) {

        System.out.print(" ");
        for (int i = 0; i < n; i++) {
            System.out.print("   " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println();
        }
    } // drawBoard
}
