import java.util.Scanner;
class Player {
    String name;
    int possibleMoves;
    int rowPosition;
    int columnPosition;
}
class Gameplay {
    Scanner input = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    String[][] isola = new String[7][7];
    Player player1 = new Player();
    Player player2 = new Player();
    boolean[][] visited = new boolean[7][7];
    Gameplay() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                isola[i][j] = i + " , " + j;
            }
        }
        printBoard(isola);
        gamePlay(isola);
    }
    void printBoard(String[][] isola) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (visited[i][j]) {
                    isola[i][j] = "X";
                }
                System.out.printf("%s", isola[i][j] + "         \t");
            }
            System.out.println();
        }
    }
    void gamePlay(String[][] isola) {
        // Taking input for Player 1
        System.out.print("Enter Name Of Player 1: ");
        player1.name = sc.nextLine();
        System.out.print("Enter Row Number Of Player 1: ");
        player1.rowPosition = input.nextInt();
        System.out.print("Enter Column Number Of Player 1: ");
        player1.columnPosition = input.nextInt();
        isola[player1.rowPosition][player1.columnPosition] = player1.name;
        // Taking input for Player 2
        System.out.print("Enter Name Of Player 2: ");
        player2.name = sc.nextLine();
        System.out.print("Enter Row Number Of Player 2: ");
        player2.rowPosition = input.nextInt();
        System.out.print("Enter Column Number Of Player 2: ");
        player2.columnPosition = input.nextInt();
        isola[player2.rowPosition][player2.columnPosition] = player2.name;
        printBoard(isola);
        boolean gameOver = false;
        while (!gameOver) {
            getPlayerTurn(isola, sc, player1);
            if (isGameOver()) {
                gameOver = true;
            }
            getPlayerTurn(isola, sc, player2);
            if (isGameOver()) {
                gameOver = true;
            }
        }
    }
    void getPlayerTurn(String[][] isola, Scanner sc, Player player) {
        System.out.println("\n--------<" + player.name + " Turns>--------");
        System.out.println("\nPress W to move up");
        System.out.println("Press Q to move up-Left");
        System.out.println("Press E to move up-Right");
        System.out.println("Press S to move down");
        char key = sc.next().charAt(0);
        System.out.println("------<Enter row and column to block way>------");
        System.out.print("Enter Row Number: ");
        int row = input.nextInt();
        System.out.print("Enter Column Number: ");
        int column = input.nextInt();
        if (isValidMove(row, column) && !visited[row][column]) {
            visited[row][column] = true;
        } 
        else {
            System.out.println("You lost your turn because you make an invalid move");
        }
        makeMove(player, player.rowPosition, player.columnPosition, key);
        printBoard(isola);
    }
    int checkPossibleMoves(Player player, int i, int j) {
        player1.possibleMoves = 0;
        player2.possibleMoves = 0;
        if (player.equals(player1)) {
            if (isValidMove(i + 1, j) && !visited[i + 1][j] && isola[i + 1][j] != player2.name) {
                player1.possibleMoves++;
            }
            if (isValidMove(i + 1, j + 1) && !visited[i + 1][j + 1] && isola[i + 1][j + 1] != player2.name) {
                player1.possibleMoves++;
            }
            if (isValidMove(i + 1, j - 1) && !visited[i + 1][j - 1] && isola[i + 1][j - 1] != player2.name) {
                player1.possibleMoves++;
            }
            if (isValidMove(i - 1, j) && !visited[i - 1][j] && isola[i - 1][j] != player2.name) {
                player1.possibleMoves++;
            }
        } 
        else if (player.equals(player2)) {
            if (isValidMove(i - 1, j) && !visited[i - 1][j] && isola[i - 1][j] != player1.name) {
                player2.possibleMoves++;
            }
            if (isValidMove(i - 1, j - 1) && !visited[i - 1][j - 1] && isola[i - 1][j - 1] != player1.name) {
                player2.possibleMoves++;
            }
            if (isValidMove(i - 1, j + 1) && !visited[i - 1][j + 1] && isola[i - 1][j + 1] != player1.name) {
                player2.possibleMoves++;
            }
            if (isValidMove(i + 1, j) && !visited[i + 1][j] && isola[i + 1][j] != player1.name) {
                player2.possibleMoves++;
            }
        }
        if (player.equals(player1)) {
            return player1.possibleMoves;
        } 
        else if (player.equals(player2)) {
            return player2.possibleMoves;
        }

        return -1;
    }
    boolean makeMove(Player player, int i, int j, char key) {
        switch (key) {
            case 'w': {
                if (player.equals(player1) && isValidMove(i + 1, j) && isola[i + 1][j] != player2.name) {
                    isola[i + 1][j] = isola[i][j];
                    visited[i][j] = true;
                    player1.rowPosition = i + 1;
                    player1.columnPosition = j;
                    return true;
                } 
                else if (player.equals(player2) && isValidMove(i - 1, j) && isola[i - 1][j] != player1.name) {
                    isola[i - 1][j] = isola[i][j];
                    visited[i][j] = true;
                    player2.rowPosition = i - 1;
                    player2.columnPosition = j;
                    return true;
                }
                System.out.println("You lost your turn because you tries to cross other player");
                break;
            }
            case 'q': {
                if (player.equals(player1) && isValidMove(i + 1, j - 1) && isola[i + 1][j - 1] != player2.name) {
                    isola[i + 1][j - 1] = isola[i][j];
                    visited[i][j] = true;
                    player1.rowPosition = i + 1;
                    player1.columnPosition = j - 1;
                    return true;
                } 
                else if (player.equals(player2) && isValidMove(i - 1, j - 1) && isola[i - 1][j - 1] != player1.name) {
                    isola[i - 1][j - 1] = isola[i][j];
                    visited[i][j] = true;
                    player2.rowPosition = i - 1;
                    player2.columnPosition = j - 1;
                    return true;
                }
                System.out.println("You lost your turn because you tries to cross other player");
                break;
            }
            case 'e': {
                if (player.equals(player1) && isValidMove(i + 1, j + 1) && isola[i + 1][j + 1] != player2.name) {
                    isola[i + 1][j + 1] = isola[i][j];
                    visited[i][j] = true;
                    player1.rowPosition = i + 1;
                    player1.columnPosition = j + 1;
                    return true;
                } 
                else if (player.equals(player2) && isValidMove(i - 1, j + 1) && isola[i - 1][j + 1] != player1.name) {
                    isola[i - 1][j - 1] = isola[i][j];
                    visited[i][j] = true;
                    player2.rowPosition = i - 1;
                    player2.columnPosition = j - 1;
                    return true;
                }
                System.out.println("You lost your turn because you tries to cross other player");
                break;
            }
            case 's': {
                if (player.equals(player1) && isValidMove(i - 1, j) && isola[i - 1][j] != player2.name) {
                    isola[i - 1][j] = isola[i][j];
                    visited[i][j] = true;
                    player1.rowPosition = i - 1;
                    player1.columnPosition = j;
                    return true;
                } 
                else if (player.equals(player2) && isValidMove(i + 1, j) && isola[i + 1][j] != player1.name) {
                    isola[i + 1][j] = isola[i][j];
                    visited[i][j] = true;
                    player2.rowPosition = i + 1;
                    player2.columnPosition = j;
                    return true;
                }
                System.out.println("You lost your turn because you tries to cross other player");
                break;
            }
            default: {
                System.out.println("You lost your turn because you make an invalid move");
            }
        }
        return false;
    }
    boolean isValidMove(int i, int j) {
        if (i < 0 || i > 6 || j < 0 || j > 6) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        return true;
    }
    boolean isGameOver() {
        if (checkPossibleMoves(player1, player1.rowPosition, player1.columnPosition) == 0) {
            System.out.println("Player 2 Is The Winner");
            return true;
        }
        if (checkPossibleMoves(player2, player2.rowPosition, player2.columnPosition) == 0) {
            System.out.println("Player 1 Is The Winner");
            return true;
        }
        return false;
    }
}
public class Isola {
    public static void main(String[] args) {
        Gameplay gameplay = new Gameplay();
    }
}
