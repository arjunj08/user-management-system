# Quick Reference Guide

## Getting Started

### 1. First Time Setup
```
Run the application → MainView appears → Start adding users
```

### 2. Adding Your First User
- Enter User ID (or click "Generate ID")
- Enter Name (e.g., "John Doe")
- Enter Email (e.g., "john@example.com")
- Enter Class (e.g., "Class 10A")
- Enter DOB (format: 15/06/2005)
- Click "Add User"
- User appears in table instantly

### 3. Editing a User
- Click the user row in the table
- Form auto-populates with their data
- Modify the fields
- Click "Update User"

### 4. Finding a User
- Select search type: ID, Name, Email, or Class
- Enter search term
- Click "Search"
- Results appear in table

### 5. Deleting a User
- Click the user row to select them
- Click "Delete User"
- Confirm deletion
- User removed instantly

---

## Menu Quick Reference

### File Menu
| Option | Purpose | Shortcut |
|--------|---------|----------|
| New User | Clear form for new entry | - |
| Export to CSV | Save all users to CSV | - |
| Create Backup | Backup all data | - |
| Restore from Backup | Load previous backup | - |
| Exit | Close app | - |

### Tools Menu
| Option | Purpose | Use When |
|--------|---------|----------|
| View Statistics | Detailed report | Want insights |
| Validate All Users | Check data integrity | Before export |
| Clear All Data | Delete everything | Start over |

### Help Menu
| Option | Purpose |
|--------|---------|
| User Guide | Complete instructions |
| About | App info |

---

## Button Guide

### Left Panel
```
Add User       → Create new user record
Update User    → Modify existing user
Delete User    → Remove user record
Clear Form     → Reset all fields
Generate ID    → Auto-create unique ID
```

### Top Bar
```
Search         → Find users by criteria
Clear          → Reset search results
Export CSV     → Save to CSV file
Refresh        → Reload table
Exit           → Close application
```

### Tabs
```
Users          → User list view
Dashboard      → Statistics view
History        → Operation log view
```

---

## Date Format

**Always use: dd/MM/yyyy**

Examples:
- 15/06/2005 ✓ (Correct)
- 6/15/2005 ✗ (Wrong)
- 2005-06-15 ✗ (Wrong)
- 15-06-2005 ✗ (Wrong)

Age is calculated automatically based on this date.

---

## Email Format

**Valid formats:**
- user@example.com ✓
- john.doe@company.co.uk ✓
- test+tag@domain.com ✓

**Invalid formats:**
- user@.com ✗
- user.example.com ✗
- @example.com ✗

---

## Keyboard Shortcuts

| Action | How |
|--------|-----|
| Navigate fields | Tab key |
| Confirm search | Press Enter |
| Select all text | Ctrl+A |
| Clear field | Ctrl+A then Delete |

---

## Common Tasks & Solutions

### Task: Add 10 users quickly
1. Clear form with "Clear Form"
2. Click "Generate ID" for each user
3. Fill name, email, class, DOB
4. Click "Add User"
5. Repeat

### Task: Find users from Class 10A
1. Select "Class" from search dropdown
2. Type "10A"
3. Click Search
4. All students from Class 10A appear

### Task: Back up your data
1. File → Create Backup
2. Backup is created with timestamp
3. Stored in backups/ folder

### Task: Restore from backup
1. File → Restore from Backup
2. Select backup from list
3. Click OK
4. Data restored instantly

### Task: Export to Excel
1. Click "Export CSV"
2. Saved as users_data.csv
3. Open in Excel/Sheets

### Task: Check statistics
1. Click "Dashboard" tab
2. Or File → Tools → View Statistics
3. See detailed report

### Task: Clear all data
1. Tools → Clear All Data
2. Confirm (twice for safety)
3. All users deleted

---

## Tips & Tricks

1. **Smart IDs**: Use auto-generated IDs to avoid duplicates
2. **Quick Edit**: Click table row → data loads → update form → click Update
3. **Live Age**: Age shows automatically as soon as you enter DOB
4. **Search Tips**: Name/Email search is case-insensitive and partial
5. **Before Export**: Use Tools → Validate All Users first
6. **Safe Delete**: Always confirm before deleting users
7. **Backups**: Create regular backups (especially before bulk operations)
8. **Phone Optional**: Phone and Address fields are optional
9. **Class Naming**: Use consistent class names for better statistics
10. **Check Dashboard**: Regular statistics provide valuable insights

---

## Error Messages & Solutions

| Error | Cause | Solution |
|-------|-------|----------|
| "User ID already exists" | ID is duplicate | Use Generate ID or choose different ID |
| "Invalid email format" | Email malformed | Check @ symbol and domain |
| "Invalid date format" | Wrong date format | Use dd/MM/yyyy format |
| "Fill all required fields" | Missing data | Complete all non-optional fields |
| "User not found" | ID doesn't exist | Check correct ID in search |
| "No users to export" | Table is empty | Add users first |

---

## Field Requirements

### Required Fields ⚠️
- **ID** - Must be unique
- **Name** - At least 2 characters, no numbers
- **Email** - Valid email format
- **Class** - Class/batch name
- **DOB** - dd/MM/yyyy format, not in future

### Optional Fields ℹ️
- **Phone** - If provided, must be valid
- **Address** - If provided, min 5 characters

---

## Data Security

### Protected Operations
- Delete requires confirmation
- Clear All requires double confirmation
- Age cannot be in future (DOB validation)

### Data Integrity
- Email format validated
- Phone format validated
- Date logic validated
- Name format validated

### Backup Protection
- Create backups regularly
- Multiple backups stored
- Restore anytime needed

---

## Statistics Explained

| Stat | Means |
|------|-------|
| Total Users | Number of user records |
| Avg Age | Average age of all users |
| Youngest User | Age of youngest user |
| Oldest User | Age of oldest user |
| Most Common Class | Class with most students |
| Email Domains | How many different email providers |
| Class Distribution | Number of students per class |

---

## File Output Reference

### Generated Files

**users_data.csv**
- Location: Home directory
- Format: Comma-separated values
- Columns: ID, Name, Email, Class, DOB, Age
- Usage: Import to Excel/Sheets

**backup_yyyyMMdd_HHmmss.dat**
- Location: backups/ folder
- Format: Binary serialized objects
- Usage: Restore functionality
- Multiple files supported

**app_settings.properties**
- Location: Home directory
- Format: Key=value pairs
- Usage: App settings storage
- Auto-created if missing

---

## Advanced Features

### Operation History
- See all operations: Click "History" tab
- Shows add/update/delete operations
- Includes timestamps
- Helps track changes

### Detailed Statistics
- Click "Dashboard" tab or Tools → View Statistics
- Shows comprehensive analysis
- Age distribution
- Class statistics
- Recent operations

### Validation Report
- Tools → Validate All Users
- Shows data quality
- Identifies any issues
- Helps maintain data integrity

---

## Troubleshooting

### Application Won't Start
- Ensure Java 11+ is installed
- Check PATH variable

### Age Not Calculating
- Check date format (dd/MM/yyyy)
- Future dates won't calculate

### Search Not Working
- Use partial text for Name/Email
- Use exact text for ID/Class

### Backup Not Found
- Create one first (File → Create Backup)
- Check backups/ folder exists

### Email Validation Fails
- Ensure @ symbol present
- Check domain format (xxx.xxx)

---

## Best Practices

1. ✓ Create backups regularly
2. ✓ Use consistent class names
3. ✓ Validate data before export
4. ✓ Check statistics monthly
5. ✓ Confirm before delete/clear
6. ✓ Use auto-generated IDs
7. ✓ Export before major changes
8. ✓ Read error messages carefully

---

## Support

For help:
1. Check "Help" menu
2. Read error messages
3. See User Guide in Help menu
4. Review this quick reference
5. Check FEATURES.md for details

---

*Last Updated: April 2026*
*For User Management System Pro v2.0*
