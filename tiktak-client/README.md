# TikTak Game Client

This is the client application for the TikTak (Tic-Tac-Toe) game.

## Project Structure

```
src/
├── client/              # Client-side application code
│   ├── clientui/       # JavaFX UI components
│   ├── network/        # Network communication (ClientSession)
│   └── single/         # Single-player game modes (Easy, Medium, Hard)
├── req/                # Shared request/response classes
├── res/                # CSS resources for UI styling
└── shared/             # Shared data models (SavedGame)
```

## Requirements

- Java 8 or higher
- JavaFX library
- Network connection to server

## Running the Client

1. Ensure the server is running on `localhost:5000` (or update the connection settings)
2. Compile all Java files in the `src` directory
3. Run `client.clientui.ClientMainApp`

## Features

- **Single Player Mode**: Play against AI with three difficulty levels

  - Easy: Basic AI moves
  - Medium: Intermediate AI strategy
  - Hard: Advanced AI with optimal moves

- **Multiplayer Mode**:
  - Connect to server
  - View online players
  - Send/receive game invitations
  - Real-time gameplay
  - In-game chat
  - Save/load games

## Connection

The client connects to the server using socket communication on port 5000.
