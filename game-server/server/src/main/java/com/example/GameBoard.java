package com.example;

import com.example.model.Player;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final int SIZE = 5;
    private List<Player> players;
    private char[][] board;
    private Player currentPlayer;

    public GameBoard() {
        board = new char[SIZE][SIZE];
        players = new ArrayList<>();
        initializeBoard();
        currentPlayer = null;
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean isValidMove(int x, int y, Player player) {
        // Implement movement logic here
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    public void makeMove(int x, int y, Player player) {
        if (isValidMove(x, y, player)) {
            board[x][y] = player.getSymbol();
            // Switch turn
            currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        }
    }

    public List<int[]> getValidMoves(int x, int y) {
        // Return list of valid moves for a given position
        return new ArrayList<>();
    }

    public boolean isGameOver() {
        // Implement game over logic here
        return false;
    }

    public char[][] getBoard() {
        return board;
    }

    public void addPlayer(Player player) {
        players.add(player);
        if (currentPlayer == null) {
            currentPlayer = player;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
