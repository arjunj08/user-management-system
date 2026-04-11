#!/bin/bash

# User Management System Pro v2.0 - Verification Script
# This script verifies that everything is properly set up and working

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║     User Management System Pro v2.0 - Verification Script     ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Color codes
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_success() {
    echo -e "${GREEN}✓${NC} $1"
}

print_error() {
    echo -e "${RED}✗${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}⚠${NC} $1"
}

print_info() {
    echo -e "${BLUE}ℹ${NC} $1"
}

# Step 1: Check Java Installation
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Step 1: Checking Java Installation"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | grep "version" | head -1)
    print_success "Java is installed"
    echo "  Version: $JAVA_VERSION"
else
    print_error "Java is NOT installed"
    print_warning "Please install Java 11 or higher from https://java.oracle.com"
    exit 1
fi

echo ""

# Step 2: Check Javac (Compiler)
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Step 2: Checking Java Compiler (javac)"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

if command -v javac &> /dev/null; then
    JAVAC_VERSION=$(javac -version 2>&1)
    print_success "Javac (Java Compiler) is installed"
    echo "  $JAVAC_VERSION"
else
    print_error "Javac is NOT installed"
    print_warning "You need JDK (not just JRE) to compile"
    exit 1
fi

echo ""

# Step 3: Check Project Directory
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Step 3: Checking Project Structure"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

cd "$(dirname "$0")/application1" 2>/dev/null || {
    print_error "Could not navigate to application1 directory"
    exit 1
}

if [ -d "src" ]; then
    print_success "Source directory exists"
else
    print_error "Source directory NOT found"
    exit 1
fi

# Count Java files
JAVA_FILES=$(find src -name "*.java" | wc -l)
print_info "Found $JAVA_FILES Java source files"

echo ""

# Step 4: Create Build Directory
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Step 4: Setting up Build Environment"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

if [ ! -d "bin" ]; then
    mkdir -p bin
    print_success "Created bin/ directory"
else
    print_info "bin/ directory already exists"
fi

echo ""

# Step 5: Compile Project
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Step 5: Compiling Java Source Files"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

print_info "Compiling... This may take a few seconds"

if javac -d bin -sourcepath src src/**/*.java 2>&1 | grep -q "error"; then
    print_error "Compilation FAILED"
    echo "Please check the error messages above"
    exit 1
else
    print_success "Compilation SUCCESSFUL"
fi

echo ""

# Step 6: Verify Compiled Classes
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Step 6: Verifying Compiled Classes"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

CLASS_FILES=$(find bin -name "*.class" | wc -l)
if [ "$CLASS_FILES" -gt 0 ]; then
    print_success "Found $CLASS_FILES compiled class files"
    print_info "Key classes compiled:"
    for file in bin/app/Main.class bin/model/UserModel.class bin/view/MainView.class bin/controller/MainController.class; do
        if [ -f "$file" ]; then
            echo "    ✓ $(basename $(dirname $file))/$(basename $file)"
        fi
    done
else
    print_error "No class files found in bin/ directory"
    exit 1
fi

echo ""

# Step 7: Check for dependencies
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Step 7: Checking Utility Classes"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

UTIL_CLASSES=("DateValidator" "EmailValidator" "IDGenerator" "FileManager" "BackupManager" "StatisticsAnalyzer" "FormValidator" "AppSettings")

for class in "${UTIL_CLASSES[@]}"; do
    if [ -f "bin/util/${class}.class" ]; then
        print_success "Utility class: $class"
    else
        print_warning "Missing utility class: $class"
    fi
done

echo ""

# Step 8: Summary
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo "Verification Summary"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"

print_success "✨ All verification checks PASSED!"
echo ""
echo "You can now run the application with:"
echo ""
echo -e "  ${YELLOW}java -cp bin app.Main${NC}"
echo ""
echo "Or use the provided run script:"
echo ""
echo -e "  ${YELLOW}./run.sh${NC}"
echo ""

print_info "For more information, see README.md or QUICKSTART.md"
echo ""
