#!/bin/bash
# Simple client runner - handles JavaFX dependency issue

echo "=== TikTak Client Runner ==="
echo ""

# Check if we can compile without JavaFX first
echo "Attempting to compile core classes..."
javac -d classes src/req/*.java src/shared/*.java src/client/single/*.java 2>/dev/null

if [ $? -eq 0 ]; then
    echo "✓ Core classes compiled successfully"
    echo ""
    echo "⚠ JavaFX UI classes require JavaFX libraries"
    echo ""
    echo "To fix the 'javafx cannot be resolved' error:"
    echo ""
    echo "Option 1 - Install system JavaFX:"
    echo "  sudo apt install openjfx libopenjfx-java"
    echo ""
    echo "Option 2 - Download JavaFX manually:"
    echo "  wget https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_linux-x64_bin-sdk.zip"
    echo "  unzip openjfx-11.0.2_linux-x64_bin-sdk.zip"
    echo "  javac --module-path javafx-sdk-11.0.2/lib --add-modules javafx.controls -d classes src/**/*.java"
    echo ""
    echo "Option 3 - Use Oracle JDK 8 (includes JavaFX)"
    echo ""
    echo "Current status: Core logic compiled, UI requires JavaFX"
else
    echo "✗ Compilation failed"
fi

