/*
FCAI – Software Engineering – 2025 - Assignment 1
----------------------------------------------------
Author: Eman Emad Abdulrahim Farghaly

ID : 20230619->3,6

Section : 23

Application : Tic-Tac-Toe Games with 2 players option or playing against random computer
*/

import java.util.Random;
import java.util.Scanner;

public class Games {
    private char[][] board;
    private int n_moves;
    private char currentPlayer;
    private int scoreX;
    private int scoreO;
    private String playerX;
    private String playerO;


    public Games(int g) {
        board = new char[g][g];
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < g; j++) {
                board[i][j] = ' ';
            }
        }
        n_moves = 0;
        currentPlayer = 'X';
        scoreX = 0;
        scoreO = 0;
    }

    public boolean updateBoard(int x, int y,int g, char symbol) {
        if (x >= 0 && x < g && y >= 0 && y < g && board[x][y] == ' ') {
            board[x][y] = symbol;
            n_moves++;
            return true;
        }
        return false;
    }

    public void displayBoard(int g) {
        for (int i = 0; i < g; i++) {
            System.out.print("| ");
            for (int j = 0; j < g; j++) {
                System.out.print("(" + i + "," + j + ") " + board[i][j] + " |");
            }
            System.out.println("\n-----------------------------------");
        }
        if (g==5){
        System.out.println("Score - " + playerX + ": " + scoreX + " | " + playerO + ": " + scoreO);
        }
    }


    public int countThrees(char player) {
        int count = 0;

        // Check rows & columns
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player) {
                    count++;
                }
                if (board[j][i] == player && board[j + 1][i] == player && board[j + 2][i] == player) {
                    count++;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player) {
                    count++;
                }
                if (board[i + 2][j] == player && board[i + 1][j + 1] == player && board[i][j + 2] == player) {
                    count++;
                }
            }
        }
        return count;
    }
    public boolean gameXOWin() {

        // Check rows & columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') ||
            (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
        (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }

        return false;
    }

    public boolean gameXOIsOver() {
        return gameXOWin()||(!gameXOWin()&&n_moves == 9);
    }

    public boolean game5x5IsOver() {
        return n_moves >= 24;
    }

    public void getPlayerMove(int g) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("\n" + (currentPlayer == 'X' ? playerX : playerO) + "'s turn. Enter x and y (0 to " + (g - 1) + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter two numbers.");
                scanner.next(); // Discard invalid input
            }
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while (!updateBoard(x, y, g, currentPlayer));
    }
    public void getRandMove(int g) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(g);
            y = rand.nextInt(g);
        } while (!updateBoard(x, y, g, currentPlayer));
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void play5x5Game(boolean isai) {
        while (!game5x5IsOver()) {
            displayBoard(5);
            if (currentPlayer == 'O' && isai) {
                getRandMove(5);
            } else {
                getPlayerMove(5);
            }

            scoreX = countThrees('X');
            scoreO = countThrees('O');

            if (!game5x5IsOver()) {
                switchPlayer();
            }
        }

        displayBoard(5);
        if (scoreX > scoreO) {
            System.out.println(playerX + " wins with score: " + scoreX);
        } else if (scoreO > scoreX) {
            System.out.println(playerO + " wins with score: " + scoreO);
        } else {
            System.out.println("It's a draw!");
        }
    }

    public void playXOGame(boolean isai) {
        while (!gameXOIsOver()) {
            displayBoard(3);
            if (currentPlayer == 'O' && isai ) {
                getRandMove(3);
                if (gameXOWin()) {
                    System.out.println((currentPlayer == 'X' ? playerX : playerO) + " wins ");
                    return;
                }
                else{
                    switchPlayer();
                }
            }

            else {
                getPlayerMove(3);
                if (gameXOWin()) {
                    System.out.println((currentPlayer == 'X' ? playerX : playerO) + " wins ");
                    return;
                }
                else{
                    switchPlayer();
                }
            }
        }
        if (gameXOIsOver()&&!gameXOWin()){
            System.out.println(" Draw!");
        }
    }
    public void playMisereGame(boolean isai) {
        while (!gameXOIsOver()) {
            displayBoard(3);
            if (currentPlayer == 'O' && isai ) {
                getRandMove(3);
            } else {
                getPlayerMove(3);
            }

            if (gameXOWin()) {
                System.out.println((currentPlayer == 'X' ? playerO : playerX) + " Wins");
                return;
            }

            else{
                switchPlayer();
            }
        }
        if (gameXOIsOver()&&!gameXOWin()){
            System.out.println(" Draw!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Welcome to Tic-Tac-Toe Games!\nChoose the game\n" +
                "1.Tic-Tac-Toe\n2.Misere Tic-Tac-Toe\n3.5x5 Tic-Tac-Toe\n4.Exit\n");
            int gamechoice = scanner.nextInt();
            scanner.nextLine();
            if(gamechoice<4){
            System.out.println("Enter Player 1 name (X): ");
            String nameX = scanner.nextLine();
            System.out.println("Choose opponent: 1. Another Player  2. Random Computer");
            int oppchoice = scanner.nextInt();
            boolean aiMode = oppchoice == 2;
            String nameO = aiMode ? "Random Computer" : "Player 2";
            if (!aiMode) {
                System.out.println("Enter Player 2 name (O): ");
                nameO = scanner.next();
            }

            if (gamechoice==1){
                Games game = new Games(3);
                game.playerX = nameX;
                game.playerO = nameO;
                game.playXOGame(aiMode);
            }
            else if(gamechoice==2){
                Games game = new Games(3);
                game.playerX = nameX;
                game.playerO = nameO;
                game.playMisereGame(aiMode);
            }
            else if(gamechoice==3){
                Games game = new Games(5);
                game.playerX = nameX;
                game.playerO = nameO;
                game.play5x5Game(aiMode);
            }
            }
            if (gamechoice==4){
                System.out.println("GoodBye:)");
                break;
            }
        }
    }
}
