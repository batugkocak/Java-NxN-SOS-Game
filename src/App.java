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
                    didScore = Check(step.getRow(), step.getColumn(), step.getCharacter(), n, board);
                    drawBoard(n, board);

                } while (didScore);

            }
        }

    }

    private static Boolean Check(int row, int column, String type, int n, String board[][]) {

        Boolean didScore = false;

        if (type.equals("O")) {
            System.out.println("OOOOOOOO");

            for (int i = 0; i < 4; i++) {
                String r1 = "";
                String r2 = "";
                String r3 = "";

                if (i == 0) {
                    r1 = board[row][column - 1];
                    r2 = board[row][column];
                    r3 = board[row][column + 1];
                    if (isSOS(r1, r2, r3)) {
                        didScore = true;
                        playerScore++;
                        break;
                    }
                } else if (i == 1) {
                    r1 = board[row - 1][column];
                    r2 = board[row][column];
                    r3 = board[row + 1][column];
                    if (isSOS(r1, r2, r3)) {
                        didScore = true;
                        playerScore++;
                        break;
                    }
                }

                else if (i == 2) {
                    r1 = board[row - 1][column - 1];
                    r2 = board[row][column];
                    r3 = board[row + 1][column + 1];
                    if (isSOS(r1, r2, r3)) {
                        didScore = true;
                        playerScore++;
                        break;
                    }

                } else if (i == 3) {
                    r1 = board[row - 1][column + 1];
                    r2 = board[row][column];
                    r3 = board[row + 1][column - 1];
                    if (isSOS(r1, r2, r3)) {
                        didScore = true;
                        playerScore++;
                        break;
                    }
                }
            }

        } else if (type.equals("S"))

        {
            System.out.println("SSSSSSS");
        }

        return didScore;
    }

    private static Step Mark() {
        Scanner input = new Scanner(System.in);

        System.out.println("S veya O, Satır ve Sütun giriniz ");
        String character = input.nextLine().toUpperCase();

        int row = input.nextInt();
        int column = input.nextInt();
        System.out.println();

        board[row - 1][column - 1] = character;

        return new Step(row - 1, column - 1, character);

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

    public static Boolean isSOS(String r1, String r2, String r3) {
        if ("SOS".equals(r1 + r2 + r3)) {
            System.out.println("TRUEEUEUEUEUUEUEU");
            return true;
        }
        return false;
    }
}