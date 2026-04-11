# 🚀 Setup & Run Guide

Complete step-by-step guide for setting up and running User Management System Pro v2.0 on any operating system.

---

## 📋 Prerequisites

Before starting, ensure you have:
- ✅ **Java 11 or higher** (JDK, not JRE)
- ✅ **Git** (optional, for cloning)
- ✅ **At least 100 MB** free disk space
- ✅ **Bash/Terminal** (or Command Prompt on Windows)

### Verify Java Installation

```bash
# Check Java version
java -version

# Check Javac (compiler)
javac -version
```

**Expected Output:**
```
java version "11.x.x" ...
javac 11.x.x
```

If these don't work, you need to install Java JDK.

---

## 🔧 Installation Instructions

### Option 1: Using Quick Run Scripts (Recommended)

**On macOS/Linux:**
```bash
cd /path/to/user-management-system/application1
chmod +x run.sh
./run.sh
```

**On Windows:**
```cmd
cd C:\path\to\user-management-system\application1
run.bat
```

✨ That's it! The application will compile and run automatically.

---

### Option 2: Manual Compilation & Run

**Step 1: Navigate to the project**
```bash
cd /workspaces/user-management-system/application1
```

**Step 2: Create build directory**
```bash
mkdir -p bin
```

**Step 3: Compile all Java files**

For macOS/Linux:
```bash
javac -d bin -sourcepath src src/**/*.java
```

For Windows (Command Prompt):
```cmd
javac -d bin -sourcepath src -encoding UTF-8 ^
    src\app\Main.java ^
    src\model\UserModel.java ^
    src\view\MainView.java ^
    src\controller\MainController.java ^
    src\util\DateValidator.java ^
    src\util\EmailValidator.java ^
    src\util\IDGenerator.java ^
    src\util\FileManager.java ^
    src\util\BackupManager.java ^
    src\util\StatisticsAnalyzer.java ^
    src\util\FormValidator.java ^
    src\util\AppSettings.java
```

**Step 4: Run the application**
```bash
# macOS/Linux/Windows
java -cp bin app.Main
```

---

## ✅ Verification

### Run Verification Script

**On macOS/Linux:**
```bash
chmod +x verify.sh
./verify.sh
```

This script will:
1. ✅ Check Java installation
2. ✅ Check Javac installation
3. ✅ Verify project structure
4. ✅ Compile the project
5. ✅ Count compiled classes
6. ✅ Verify utility classes

---

## 🎯 Verification Checklist

After running, verify these things:

### ✓ Compilation Check
```bash
# Check if bin directory has class files
ls -la bin/

# Should show directories: app/, model/, view/, controller/, util/
# And multiple .class files
```

### ✓ Application Launch Check
When you run `java -cp bin app.Main`, you should see:
- Console output: `✓ Application started successfully!`
- A GUI window titled "User Management System Pro v2.0"
- Blue header bar with buttons
- Form on the left side
- User table on the right side

### ✓ Quick Feature Test
```
1. Click "Generate ID" → Should generate a unique ID ✓
2. Enter Name: "Test User"
3. Enter Email: "test@example.com"
4. Enter Class: "Class 10"
5. Enter DOB: "15/06/2005"
6. Click "Add User" → Success message ✓
7. User appears in table ✓
```

---

## 📦 Java Installation

### macOS (Homebrew)

```bash
# Install Homebrew if not already installed
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install Java
brew install openjdk@11

# Add to PATH (add to ~/.zshrc or ~/.bash_profile)
export PATH="/usr/local/opt/openjdk@11/bin:$PATH"

# Verify
java -version
javac -version
```

### Ubuntu/Debian

```bash
# Update package manager
sudo apt update

# Install OpenJDK 11
sudo apt install openjdk-11-jdk

# Verify
java -version
javac -version
```

### Windows (Installer)

1. Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/)
2. Run installer
3. During installation, note the installation path
4. Add to PATH environment variable:
   - Control Panel → System → Advanced System Settings
   - Environment Variables → New → Path
   - Add: `C:\Program Files\Java\jdk-11.x.x\bin`
5. Restart Command Prompt
6. Verify: `java -version` and `javac -version`

---

## 🆘 Troubleshooting

### Problem: "javac: command not found"
```
✗ Cause: Java compiler not installed (only JRE installed)
✓ Solution: Install JDK instead of JRE
```

### Problem: "javac: Cannot find file"
```
✗ Cause: Wrong working directory or file structure
✓ Solution: 
  - cd to application1 directory
  - Verify src/ directory exists
  - Check file names match exactly
```

### Problem: "Exception in thread "main""
```
✗ Cause: Classpath issue
✓ Solution:
  - Ensure bin/ directory exists
  - Ensure all files compiled correctly
  - Try recompiling from scratch
```

### Problem: Email validation not working
```
✗ Cause: Invalid email format entered
✓ Solution: Use format: user@domain.com
```

### Problem: Age not calculating
```
✗ Cause: Wrong date format
✓ Solution: Use dd/MM/yyyy (e.g., 15/06/2005)
```

---

## 📂 Directory Structure After Setup

```
application1/
├── src/                          (Source files)
│   ├── app/
│   │   └── Main.java
│   ├── model/
│   │   └── UserModel.java
│   ├── view/
│   │   └── MainView.java
│   ├── controller/
│   │   └── MainController.java
│   └── util/
│       ├── DateValidator.java
│       ├── EmailValidator.java
│       ├── IDGenerator.java
│       ├── FileManager.java
│       ├── BackupManager.java
│       ├── StatisticsAnalyzer.java
│       ├── FormValidator.java
│       └── AppSettings.java
├── bin/                          (Compiled classes - auto-generated)
│   ├── app/
│   │   └── Main.class
│   ├── model/
│   │   └── UserModel.class
│   ├── view/
│   │   └── MainView.class
│   ├── controller/
│   │   └── MainController.class
│   └── util/
│       └── (8 utility .class files)
├── logs/                         (Application logs - created at runtime)
├── run.sh                        (Quick run script for Unix/Linux/macOS)
├── run.bat                       (Quick run script for Windows)
└── verify.sh                     (Verification script)
```

---

## 🎓 Next Steps

1. **Read Documentation**
   - Start with `README.md` for overview
   - Read `QUICKSTART.md` for quick reference
   - Check `FEATURES.md` for detailed features

2. **Try Features**
   - Add multiple users
   - Test search functionality
   - Export to CSV
   - Create backups

3. **Explore Code**
   - Review source files in `src/`
   - Check comments and documentation
   - Run verification script

4. **Customize (Optional)**
   - Modify colors in MainView.java
   - Add new validation rules
   - Extend functionality as needed

---

## 📞 Support

If you encounter issues:

1. **Check Documentation**
   - README.md - Overview & usage
   - QUICKSTART.md - Common tasks
   - FEATURES.md - Feature details

2. **Check Logs**
   - Application prints messages to console
   - Check error messages carefully
   - Remember Java is case-sensitive

3. **Verify Setup**
   - Run verify.sh script
   - Check Java version: `java -version`
   - Verify files exist: `ls -la src/`

4. **Common Issues**
   - See "Troubleshooting" section above
   - Check compilation output
   - Ensure all prerequisites installed

---

## ✨ Success!

If you see the application window with the title "User Management System Pro v2.0" and can add a user, everything is working correctly! 🎉

Enjoy using User Management System Pro v2.0!

---

**Last Updated:** April 11, 2026  
**Version:** 2.0 - Production Ready
