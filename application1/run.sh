#!/bin/bash

# User Management System Pro v2.0 - Quick Run Script
# This script compiles and runs the application in one command

echo "╔════════════════════════════════════════════════════════╗"
echo "║  User Management System Pro v2.0 - Quick Start        ║"
echo "╚════════════════════════════════════════════════════════╝"
echo ""

# Navigate to script directory
cd "$(dirname "$0")" || exit 1

echo "📁 Working directory: $(pwd)"
echo ""

# Create bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    echo "📦 Creating bin directory..."
    mkdir -p bin
fi

# Compile
echo "🔨 Compiling Java source files..."
javac -d bin -sourcepath src src/**/*.java 2>&1

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed. Please check the errors above."
    exit 1
fi

echo "✅ Compilation successful!"
echo ""
echo "🚀 Starting application..."
echo ""

# Run
java -cp bin app.Main

# Show message after app closes
echo ""
echo "👋 Application closed. Thank you for using User Management System Pro v2.0!"
