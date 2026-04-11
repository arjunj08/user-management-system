@echo off
REM User Management System Pro v2.0 - Quick Run Script (Windows)
REM This script compiles and runs the application in one command

echo.
echo ╔════════════════════════════════════════════════════════╗
echo ║  User Management System Pro v2.0 - Quick Start        ║
echo ╚════════════════════════════════════════════════════════╝
echo.

REM Navigate to script directory
pushd "%~dp0"

echo 📁 Working directory: %cd%
echo.

REM Create bin directory if it doesn't exist
if not exist "bin" (
    echo 📦 Creating bin directory...
    mkdir bin
)

REM Compile
echo 🔨 Compiling Java source files...
javac -d bin -sourcepath src src\app\Main.java src\model\UserModel.java src\view\MainView.java src\controller\MainController.java src\util\*.java 2>&1

if errorlevel 1 (
    echo ❌ Compilation failed. Please check the errors above.
    pause
    exit /b 1
)

echo ✅ Compilation successful!
echo.
echo 🚀 Starting application...
echo.

REM Run
java -cp bin app.Main

REM Show message after app closes
echo.
echo 👋 Application closed. Thank you for using User Management System Pro v2.0!
pause
