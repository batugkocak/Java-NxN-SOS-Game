import java.util.Random;
import java.util.Scanner;

public class App {

    static int turn; // 0 is Player, 1 is AI
    static int playerScore = 0;
    static int aiScore = 0;
    static int stepCount = 0;
    static int n;
    static String board[][];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        startGame();

        turn = 0;
        while (true) {
            if (turn == 0) {
                if (isGameFinished(n)) {
                    break;
                }

                Boolean didScore = false;
                do {

                    Step step = Mark();
                    didScore = Check(step.getRow(), step.getColumn(), step.getCharacter(), n, board);

                    stepCount++;
                    System.out.print("\033\143");
                    if (didScore == true) {
                        System.out.println("TRUE TRUE TRUE");
                    }
                    System.out.println("******************** " + stepCount + ". EL" + " ********************");
                    System.out.println("********************");
                    System.out.println();
                    drawBoard(n, board);
                    System.out.println();
                    System.out.println("********************");

                    if (didScore) {
                        playerScore++;
                    }

                    writeScore();
                    didScore = true; // TODO: for test, remove later

                } while (didScore);
                turn = 1; // if didScore is not tue
            }
            if (turn == 1) {
                if (isGameFinished(n)) {
                    break;
                }
                // AI plays
            }
        }

        System.out.println("OYUN BİTTİ İHTİYAR");
    } // Main

    private static Boolean Check(int row, int column, String type, int n, String board[][]) {

        Boolean didScore = false;

        if (type.equals("O")) {
            System.out.println("OOOOOOOO");

            for (int i = 0; i < 4; i++) {
                String r1 = "";
                String r2 = "";
                String r3 = "";

                if (i == 0 && Board.notInLeftCorner(column, n) && Board.notInRightcorner(column, n)) {
                    r1 = board[row][column - 1];
                    r2 = board[row][column];
                    r3 = board[row][column + 1];
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 1 && Board.notInUpCorner(row, n) && Board.notInDownCorner(row, n)) {
                    r1 = board[row - 1][column];
                    r2 = board[row][column];
                    r3 = board[row + 1][column];
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                }

                else if (i == 2 && Board.notInAllCorners(row, column, n)) {
                    r1 = board[row - 1][column - 1];
                    r2 = board[row][column];
                    r3 = board[row + 1][column + 1];
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }

                } else if (i == 3 && Board.notInAllCorners(row, column, n)) {
                    r1 = board[row - 1][column + 1];
                    r2 = board[row][column];
                    r3 = board[row + 1][column - 1];
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                }
            }

        } else if (type.equals("S")) {
            System.out.println("SSSSSSSSSS");

            for (int i = 0; i < 8; i++) {
                String r1 = "";
                String r2 = "";
                String r3 = "";
                if (i == 0) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row, column - 1, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 1) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row - 1, column - 1, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 2) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row - 1, column, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 3) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row - 1, column + 1, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 4) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row, column + 1, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 5) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row + 1, column + 1, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 6) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row + 1, column, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                } else if (i == 7) {
                    r1 = board[row][column];
                    r2 = getType(row, column, i);
                    r3 = getType(row + 1, column - 1, i);
                    if (isSos(r1, r2, r3)) {
                        didScore = true;
                        break;
                    }
                }
            }
        }

        return didScore;
    }// Check

    private static Step Mark() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("S veya O, Satır ve Sütun giriniz ");
        String character = input.nextLine().toUpperCase();

        int row = input.nextInt();
        int column = input.nextInt();
        System.out.println();

        board[row - 1][column - 1] = character;

        return new Step(row - 1, column - 1, character);

    } // Mark

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
    } // drawBoard

    public static Boolean isSos(String r1, String r2, String r3) {
        if ("SOS".equals(r1 + r2 + r3)) {
            return true;
        } else
            return false;
    } // isSOS

    public static Boolean isGameFinished(int n) {
        if (stepCount == n * n) { // Game End
            return true;
        } else
            return false;
    }// isGameFinished

    public static void startGame() {

        System.out.println();
        System.out.println();
        turn = new Random().nextInt(2);
        System.out.println("NxN Tahtanın Satır ve Sütun Sayısı: ");

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

    }

    private static String getType(int row, int column, int i) {
        switch (i) {
            case 0:
                if (column <= 0)
                    return "";
                return board[row][column - 1];
            case 1:
                if (row <= 0)
                    return "";
                if (column <= 0)
                    return "";
                return board[row - 1][column - 1];
            case 2:
                if (row <= 0)
                    return "";
                return board[row - 1][column];
            case 3:
                if (row <= 0)
                    return "";
                if ((column + 1) >= n)
                    return "";
                return board[row - 1][column + 1];
            case 4:
                if ((column + 1) >= n)
                    return "";
                return board[row][column + 1];
            case 5:
                if ((row + 1) >= n)
                    return "";
                if ((column + 1) >= n)
                    return "";
                return board[row + 1][column + 1];
            case 6:
                if ((row + 1) >= n)
                    return "";
                return board[row + 1][column];
            case 7:
                if ((row + 1) >= n)
                    return "";
                if (column <= 0)
                    return "";
                return board[row + 1][column - 1];
            default:
                return "";
        }
    }

    public static void finishGame() {
        if (playerScore > aiScore) {
            System.out.println("********************");
            System.out.println();
            drawBoard(n, board);
            System.out.println();
            System.out.println("********************");
            System.out.println("Oyunu Oyuncu Kazandı! Tebrikler!");
        } else if (aiScore > playerScore) {
            System.out.println("********************");
            System.out.println();
            drawBoard(n, board);
            System.out.println();
            System.out.println("********************");
            System.out.println("Oyunu Yapay Zeka Kazandı! Şansını bir daha dene!");
        } else {
            System.out.println("********************");
            System.out.println();
            drawBoard(n, board);
            System.out.println();
            System.out.println("********************");
            System.out.println("Oyun Berabere! Zorlu bir maçtı!");
        }
        scanner.close();
    }

    public static void writeScore() {
        System.out.println("Oyuncu puanı: " + playerScore);
        System.out.println("Bilgisayar puanı: " + aiScore);
    }
} // main
