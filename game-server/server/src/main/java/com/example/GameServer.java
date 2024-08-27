package com.example;

import com.example.model.Player;
import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServer extends WebSocketServer {
    private GameBoard gameBoard;
    private Map<WebSocket, Player> players = new HashMap<>();

    public GameServer(InetSocketAddress address) {
        super(address);
        gameBoard = new GameBoard();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Player player = new Player(conn);
        players.put(conn, player);
        gameBoard.addPlayer(player);
        broadcastGameState(); // Broadcast game state to all players
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try {
            JSONObject jsonMessage = new JSONObject(message);
            String type = jsonMessage.getString("type");

            switch (type) {
                case "move":
                    handleMove(conn, jsonMessage);
                    break;
                case "initialize":
                    initializeGame();
                    break;
                default:
                    sendUnknownMessageType(conn);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleMove(WebSocket conn, JSONObject jsonMessage) {
        try {
            int x = jsonMessage.getInt("x");
            int y = jsonMessage.getInt("y");
            Player player = players.get(conn);

            if (gameBoard.isValidMove(x, y, player)) {
                gameBoard.makeMove(x, y, player);
                sendValidMoves(x, y);
                if (gameBoard.isGameOver()) {
                    sendGameOver();
                } else {
                    broadcastGameState();
                }
            } else {
                sendInvalidMove(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendValidMoves(int x, int y) {
        try {
            List<int[]> validMoves = gameBoard.getValidMoves(x, y);
            JSONObject response = new JSONObject();
            response.put("type", "validMoves");
            response.put("moves", validMoves);
            for (WebSocket conn : players.keySet()) {
                conn.send(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendInvalidMove(WebSocket conn) {
        try {
            JSONObject response = new JSONObject();
            response.put("type", "invalidMove");
            conn.send(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGameOver() {
        try {
            JSONObject response = new JSONObject();
            response.put("type", "gameOver");
            response.put("winner", getWinner());
            for (WebSocket conn : players.keySet()) {
                conn.send(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getWinner() {
        // Determine the winner based on gameBoard state
        return "Player 1"; // Placeholder, replace with actual logic
    }

    private void initializeGame() {
        // Initialize game logic here
        broadcastGameState();
    }

    private void broadcastGameState() {
        try {
            JSONObject response = new JSONObject();
            response.put("type", "gameState");
            response.put("board", gameBoard.getBoard());
            response.put("currentPlayer", gameBoard.getCurrentPlayer().getSymbol());
            for (WebSocket conn : players.keySet()) {
                conn.send(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendUnknownMessageType(WebSocket conn) {
        try {
            JSONObject response = new JSONObject();
            response.put("type", "unknownMessageType");
            conn.send(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        players.remove(conn);
        if (players.isEmpty()) {
            stop();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        try {
            System.out.println("WebSocket server started successfully");
            // Example operation that might throw InterruptedException
            Thread.sleep(1000); // Example sleep call
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted");
            e.printStackTrace();
            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        GameServer server = new GameServer(new InetSocketAddress(8080));
        server.start();
    }
}
