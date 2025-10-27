#!/bin/bash
# Run script for TikTak Client

echo "=== Starting TikTak Client ==="
echo ""

# Check if compiled
if [ ! -d "classes" ]; then
    echo "Classes directory not found. Running compilation..."
    ./compile.sh
    echo ""
fi

# Run the client
echo "Connecting to server at localhost:5000..."
echo ""

java -cp classes client.clientui.ClientMainApp

# Note: If JavaFX is not in the default Java installation, use:
# java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp classes client.clientui.ClientMainApp

