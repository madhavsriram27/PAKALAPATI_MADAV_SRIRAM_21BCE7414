const socket = new WebSocket('ws://localhost:8080/game');

socket.addEventListener('open', () => {
    console.log('Connected to the server');
    // Initialize the game
    socket.send(JSON.stringify({ type: 'initialize' }));
});

socket.addEventListener('message', function(event) {
    const data = JSON.parse(event.data);
    switch (data.type) {
        case 'gameState':
            updateGameState(data);
            break;
        case 'validMoves':
            displayValidMoves(data.moves);
            break;
        case 'invalidMove':
            alert('Invalid move! Try again.');
            break;
        case 'gameOver':
            displayGameOver(data.winner);
            break;
    }
});

const gameBoard = document.getElementById('gameBoard');
for (let i = 0; i < 25; i++) {
    const cell = document.createElement('div');
    cell.className = 'cell';
    cell.dataset.index = i;
    cell.addEventListener('click', handleCellClick);
    gameBoard.appendChild(cell);
}

function updateGameState(data) {
    const cells = gameBoard.querySelectorAll('.cell');
    cells.forEach((cell, index) => {
        const x = Math.floor(index / 5);
        const y = index % 5;
        cell.textContent = data.board[x][y];
        // Update styling based on the game state
    });
    document.getElementById('playerTurn').textContent = `Current Turn: ${data.currentPlayer}`;
}

function displayValidMoves(moves) {
    const validMovesContainer = document.getElementById('validMoves');
    validMovesContainer.innerHTML = '';
    moves.forEach((move, index) => {
        const button = document.createElement('button');
        button.textContent = `Move to (${move.x}, ${move.y})`;
        button.addEventListener('click', () => sendMove(move.x, move.y));
        validMovesContainer.appendChild(button);
    });
}

function sendMove(x, y) {
    socket.send(JSON.stringify({ type: 'move', x: x, y: y }));
}

function handleCellClick(event) {
    const index = event.target.dataset.index;
    // Handle cell click for move selection
}

function displayGameOver(winner) {
    alert(`Game Over! ${winner} wins.`);
}