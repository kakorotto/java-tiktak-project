#!/bin/bash
# Quick setup script using Maven to handle dependencies

echo "=== Setting up TikTak Client with Maven ==="
echo ""

# Create pom.xml if it doesn't exist
if [ ! -f "pom.xml" ]; then
    echo "Creating pom.xml..."
    cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.tiktak</groupId>
    <artifactId>tiktak-client</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <javafx.version>11.0.2</javafx.version>
    </properties>

    <dependencies>
        <!-- JavaFX -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        
        <!-- ControlsFX -->
        <dependency>
            <groupId>org.controlsfx</groupId>
            <artifactId>controlsfx</artifactId>
            <version>11.1.0</version>
        </dependency>
    </dependencies>

    <build>
        <sourceDirectory>src</sourceDirectory>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <mainClass>client.clientui.ClientMainApp</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF
    echo "✓ pom.xml created"
else
    echo "✓ pom.xml already exists"
fi

echo ""
echo "To build and run with Maven:"
echo "  mvn clean compile"
echo "  mvn javafx:run"
echo ""
echo "Or install dependencies and run manually:"
echo "  mvn dependency:copy-dependencies"
echo "  java -cp \"target/classes:target/dependency/*\" client.clientui.ClientMainApp"

