import java.util.Random;
import java.util.Scanner;

public class App {

    static int turn; // 0 is Player, 1 is AI
    static int playerScore = 0;
    static int aiScore = 0;
    static int stepCount = 0;
    static String board[][];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println();
        System.out.println();
        turn = new Random().nextInt(2);
        System.out.println("NxN Tahtanın Satır ve Sütun Sayısı:  ");

        int n;

        n = scanner.nextInt();

        board = new String[n][n];
        for (

                int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = "_";
            }
        }

        System.out.println("********************");

        drawBoard(n, board);
        System.out.println("********************");
        turn = 0;
        while (true) {
            if (turn == 0) {
                Boolean didScore = false;
                do {
                    Step step = Mark();
                    didScore = Check(step, n, board);

                    drawBoard(n, board);

                    System.out.println(board[1][1] + " 13131232131231231213");
                    didScore = true;
                } while (didScore);
                turn = 1;
            }
        }

    }

    private static Boolean Check(Step step, int n, String board[][]) {

        Boolean didScore = false;

        final int row = step.getRow();
        final int column = step.getColumn();
        final String type = step.getCharacter();
        if (type == "O") {

            for (int i = 0; i < 4; i++) {
                String r1 = "";
                String r2 = "";
                String r3 = "";

                if (i == 0 && column != 0 && (column + 1) != n) {
                    r1 = board[row][column - 1];
                    r2 = board[row][column];
                    r3 = board[row][column + 1];
                } else if (i == 1 && row != 0 && (row + 1) != n) {
                    r1 = board[row - 1][column];
                    r2 = board[row][column];
                    r3 = board[row + 1][column];
                } else if (i == 2 && (row != 0 && column != 0) && ((row + 1) != n && (column + 1) != n)) {
                    r3 = board[row - 1][column - 1];
                    r2 = board[row][column];
                    r3 = board[row + 1][column + 1];
                } else if (i == 3 && (row != 0 && (column + 1) != n) && (column != 0 && (row + 1) != n)) {
                    r3 = board[row - 1][column + 1];
                    r2 = board[row][column];
                    r3 = board[row + 1][column - 1];
                }
                if ("SOS" == (r1 + r2 + r3)) {
                    System.out.println("TRUEEUEUEUEUUEUEU");
                    didScore = true;
                    break;
                }
            }
        }

        return didScore;
    }

    private static Step Mark() {

        System.out.println();
        System.out.println("S veya O");
        String character = scanner.next();
        System.out.println("Satır");
        int row = scanner.nextInt();
        System.out.println("Sütun");
        int column = scanner.nextInt();

        board[row - 1][column - 1] = character;

        return new Step(row, column, character);

    }

    private static void drawBoard(int n, String board[][]) {

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
    }

}
