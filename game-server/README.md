# 5x5 Game Project

## Project Structure
- **client/**: Contains client-side code (HTML, CSS, JavaScript).
- **server/**: Contains server-side code (Java).

## Client Setup
1. **Open `client/index.html` in a web browser.**

## Server Setup
1. **Navigate to the server directory**
cd server


2. **Build the Project**
mvn clean install


3. **Run the Server**
mvn exec
-Dexec.mainClass="com.example.GameServer"


## Game Rules and Functionality
- Implement the game rules and move validation on both client and server sides.
- Use WebSocket communication for real-time updates.
- The client-side code includes basic HTML, CSS, and JavaScript for the 5x5 game board.

## Edge Cases
- Handle simultaneous moves, disconnections, and game state updates properly.

## Development Notes
- Ensure clear separation between client and server code.
- Follow best practices for clean, commented code and error handling.
