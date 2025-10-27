# TikTak Client - Status Report

## ✅ Project Structure - COMPLETE

The client has been successfully separated from the server with the following structure:

```
tiktak-client/src/
├── client/
│   ├── clientui/       - JavaFX UI components (7 files)
│   ├── network/        - ClientSession.java (network communication)
│   └── single/         - AI opponents (Easy, Medium, Hard)
├── req/                - Request communication classes (2 files)
├── res/                - CSS stylesheets (9 files)
└── shared/             - SavedGame.java (shared data model)
```

**Total: 14 Java source files + 9 CSS files**

## ⚠️ Dependencies Required

### 1. JavaFX (REQUIRED)

The client uses JavaFX for its graphical user interface. Your current Java 11 installation does not include JavaFX by default.

**Used JavaFX Components:**

- `javafx.application.Application`
- `javafx.scene.*` (Scene, controls, layouts)
- `javafx.stage.Stage`

### 2. ControlsFX (REQUIRED)

The client uses ControlsFX for notifications:

- `org.controlsfx.control.Notifications`

### 3. Server Connection

- Connects to `localhost:5000`
- Server must be running before starting the client

## 🔧 Setup Options

### Option 1: Install OpenJFX (Recommended for Linux)

```bash
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install openjfx libopenjfx-java

# Fedora
sudo dnf install java-openjfx

# Arch Linux
sudo pacman -S java-openjfx
```

### Option 2: Download JavaFX SDK

1. Download from: https://gluonhq.com/products/javafx/
2. Extract to a location (e.g., `/opt/javafx-sdk-11/`)
3. Compile with module path:

```bash
javac --module-path /opt/javafx-sdk-11/lib \
      --add-modules javafx.controls,javafx.fxml \
      -cp classes -d classes src/client/clientui/*.java
```

4. Run with module path:

```bash
java --module-path /opt/javafx-sdk-11/lib \
     --add-modules javafx.controls,javafx.fxml \
     -cp classes client.clientui.ClientMainApp
```

### Option 3: Use Maven or Gradle

Create a `pom.xml` or `build.gradle` to manage dependencies automatically.

#### Maven (pom.xml):

```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>11.0.2</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>11.0.2</version>
    </dependency>
    <dependency>
        <groupId>org.controlsfx</groupId>
        <artifactId>controlsfx</artifactId>
        <version>11.1.0</version>
    </dependency>
</dependencies>
```

### Option 4: Use Oracle JDK 8

Oracle JDK 8 includes JavaFX by default (no additional setup needed).

## 📝 Next Steps

1. **Install JavaFX** using one of the options above
2. **Download ControlsFX JAR** from Maven Central
3. **Start the server** (must be running first)
4. **Compile the client** with JavaFX in the classpath
5. **Run the client**

## 🚀 Quick Start (Once Dependencies Are Installed)

```bash
# Make sure server is running on port 5000

# Compile (if JavaFX is in system path)
./compile.sh

# Or compile with JavaFX SDK
javac --module-path /path/to/javafx-sdk/lib \
      --add-modules javafx.controls,javafx.fxml \
      -cp classes:controlsfx.jar \
      -d classes \
      src/**/*.java

# Run
java --module-path /path/to/javafx-sdk/lib \
     --add-modules javafx.controls,javafx.fxml \
     -cp classes:controlsfx.jar \
     client.clientui.ClientMainApp
```

## 📊 Compilation Status

- ✅ Shared classes (req, shared) - No dependencies
- ✅ Single-player AI - No dependencies
- ❌ UI classes - Requires JavaFX + ControlsFX
- ❌ Network layer - Requires UI classes

**Current blocker:** JavaFX not available in Java 11 installation
