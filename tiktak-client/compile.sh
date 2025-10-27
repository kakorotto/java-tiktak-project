#!/bin/bash
# Compilation script for TikTak Client

echo "=== Compiling TikTak Client ==="
echo ""

# Clean previous build
echo "Cleaning previous build..."
rm -rf bin classes
mkdir -p classes

# Check for Java version
echo "Java version:"
java -version 2>&1 | head -1
echo ""

# Compile shared classes first (no dependencies)
echo "[1/4] Compiling shared classes (req, shared)..."
javac -d classes src/req/*.java src/shared/*.java 2>&1

if [ $? -ne 0 ]; then
    echo "✗ Failed to compile shared classes"
    exit 1
fi

# Compile single-player AI (no JavaFX dependency)
echo "[2/4] Compiling single-player AI..."
javac -cp classes -d classes src/client/single/*.java 2>&1

if [ $? -ne 0 ]; then
    echo "✗ Failed to compile single-player AI"
    exit 1
fi

# Try to compile UI classes (requires JavaFX)
echo "[3/4] Compiling UI classes (requires JavaFX)..."
javac -cp classes -d classes src/client/clientui/*.java 2>&1

if [ $? -ne 0 ]; then
    echo ""
    echo "⚠ UI compilation failed - JavaFX not found!"
    echo ""
    echo "JavaFX is required. Please install JavaFX or use a JDK with JavaFX bundled."
    echo ""
    echo "Options:"
    echo "  1. Install OpenJFX: sudo apt install openjfx"
    echo "  2. Download JavaFX SDK from https://openjfx.io/"
    echo "  3. Use Oracle JDK 8 (includes JavaFX)"
    echo ""
    echo "Then compile with:"
    echo "  javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp classes -d classes src/client/clientui/*.java"
    exit 1
fi

# Compile client network layer (depends on UI classes)
echo "[4/4] Compiling network layer..."
javac -cp classes -d classes src/client/network/*.java 2>&1

if [ $? -ne 0 ]; then
    echo "✗ Failed to compile network layer"
    exit 1
fi

# Success!
echo ""
echo "✓ Compilation successful!"
echo ""
CLASS_COUNT=$(find classes -name "*.class" 2>/dev/null | wc -l)
echo "Compiled $CLASS_COUNT class files"
echo ""
echo "To run the client:"
echo "  java -cp classes client.clientui.ClientMainApp"
echo ""
echo "If you need JavaFX modules:"
echo "  java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp classes client.clientui.ClientMainApp"

