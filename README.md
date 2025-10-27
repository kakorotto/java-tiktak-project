# Java TikTak Project - Client-Server Architecture

This project has been restructured from a single-source application into a proper client-server architecture with separate applications.

## Project Structure

```
java-tiktak-project/
├── tiktak-client/          # Client Application
│   ├── src/
│   │   ├── client/        # Client UI and logic
│   │   ├── req/           # Shared request classes
│   │   ├── res/           # CSS resources
│   │   └── shared/        # Shared data models
│   └── README.md          # Client documentation
│
├── tiktak-server/          # Server Application
│   ├── src/
│   │   ├── server/        # Server logic
│   │   ├── db/            # Database connection
│   │   └── req/           # Shared request classes
│   └── README.md          # Server documentation
│
├── src-old/               # Original combined source (preserved)
└── requests-old/          # Original requests folder (preserved)
```

## Quick Start

### 1. Start the Server

```bash
cd tiktak-server
# Compile the server application
javac -d bin src/**/*.java
# Run the server
java -cp bin server.ServerMainApp
```

**Note**: Ensure MySQL database is set up first (see `tiktak-server/README.md`)

### 2. Start the Client(s)

```bash
cd tiktak-client
# Compile the client application
javac -d bin src/**/*.java
# Run the client
java -cp bin client.clientui.ClientMainApp
```

You can run multiple client instances to test multiplayer functionality.

## Architecture Overview

### Client Application

- **JavaFX-based UI** for game interface
- **Network layer** for server communication
- **Single-player mode** with AI opponents
- **Multiplayer mode** with online matchmaking

### Server Application

- **Socket server** listening on port 5000
- **Session management** for multiple connected clients
- **MySQL database** for persistence
- **Game state coordination** between players

### Communication Protocol

Both applications use a shared `Request`/`RequestType` system for communication:

- Serialized Java objects over TCP sockets
- Request types include: LOGIN, SIGNUP, SEND_MOVE, SEND_MSG, etc.

## Development

Each application can now be:

- Developed independently
- Deployed separately
- Tested in isolation
- Compiled with different build tools

## Migration Notes

The original combined source code has been preserved in:

- `src-old/` - Original src directory
- `requests-old/` - Original requests directory

These can be safely deleted once you've verified the new structure works correctly.

## Dependencies

### Client

- Java 8+
- JavaFX

### Server

- Java 8+
- MySQL 5.7+ or MariaDB
- MySQL Connector/J

## More Information

- See `tiktak-client/README.md` for client-specific documentation
- See `tiktak-server/README.md` for server-specific documentation and database setup
