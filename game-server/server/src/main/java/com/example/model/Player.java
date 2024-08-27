package com.example.model;

import org.java_websocket.WebSocket;

public class Player {
    private WebSocket connection;
    private char symbol;

    public Player(WebSocket connection) {
        this.connection = connection;
        this.symbol = ' '; // Default symbol; can be set later
    }

    public WebSocket getConnection() {
        return connection;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
