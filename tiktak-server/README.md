# TikTak Game Server

This is the server application for the TikTak (Tic-Tac-Toe) game.

## Project Structure

```
src/
├── server/             # Server-side application code
│   ├── Server.java            # Main server socket handler
│   ├── ServerSession.java     # Individual client session handler
│   ├── ServerMainApp.java     # Server UI application
│   ├── OnlinePlayer.java      # Online player representation
│   └── SavedGame.java         # Saved game data model
├── db/                 # Database connection and models
│   ├── DatabaseConnection.java  # MySQL database handler
│   ├── Player.java             # Player data model
│   └── UserAccount.java        # User account model
└── req/                # Shared request/response classes
```

## Requirements

- Java 8 or higher
- MySQL database server
- MySQL Connector/J driver

## Database Setup

1. Create a MySQL database named `TicTacToe`
2. Create the following tables:

### Player Table

```sql
CREATE TABLE player (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    score INT DEFAULT 0
);
```

### Game Table

```sql
CREATE TABLE game (
    id INT PRIMARY KEY AUTO_INCREMENT,
    player1 VARCHAR(255) NOT NULL,
    player2 VARCHAR(255) NOT NULL,
    pos11 VARCHAR(1),
    pos12 VARCHAR(1),
    pos13 VARCHAR(1),
    pos21 VARCHAR(1),
    pos22 VARCHAR(1),
    pos23 VARCHAR(1),
    pos31 VARCHAR(1),
    pos32 VARCHAR(1),
    pos33 VARCHAR(1)
);
```

## Configuration

Update database connection settings in `DatabaseConnection.java`:

- **Host**: `localhost:3306`
- **Database**: `TicTacToe`
- **Username**: `root`
- **Password**: `root`

## Running the Server

1. Ensure MySQL is running with the correct database setup
2. Compile all Java files in the `src` directory
3. Run `server.ServerMainApp`
4. The server will listen on port 5000

## Features

- **User Authentication**: Sign up and login functionality
- **Session Management**: Track online players and their status
- **Game Coordination**:
  - Match players together
  - Handle game invitations
  - Relay moves between players
  - Save/load games from database
- **Persistent Storage**:
  - Store player accounts
  - Save game progress
  - Track player scores
- **Real-time Communication**:
  - Broadcast online player lists
  - Handle in-game chat
  - Notify players of events

## Server Port

Default port: **5000**
