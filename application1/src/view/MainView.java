package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JFrame {

    /* TEXT FIELDS */

    public JTextField idField = new JTextField();
    public JTextField nameField = new JTextField();
    public JTextField emailField = new JTextField();
    public JTextField classNameField = new JTextField();
    public JTextField dobField = new JTextField();
    public JTextField phoneField = new JTextField();
    public JTextField addressField = new JTextField();

    public JTextField searchField = new JTextField(10);
    public JComboBox<String> searchTypeCombo = new JComboBox<>(
            new String[]{"ID", "Name", "Email", "Class"});

    public JTextField searchValueField = new JTextField(10);

    public JLabel ageLabel = new JLabel("Age: --");

    /* BUTTONS */

    public JButton addBtn = new JButton("Add User");
    public JButton updateBtn = new JButton("Update User");
    public JButton deleteBtn = new JButton("Delete User");
    public JButton resetBtn = new JButton("Clear Form");
    public JButton generateBtn = new JButton("Generate ID");

    public JButton searchBtn = new JButton("Search");
    public JButton clearSearchBtn = new JButton("Clear");

    public JButton exportBtn = new JButton("Export CSV");
    public JButton refreshBtn = new JButton("Refresh");

    public JButton tabUsers = new JButton("Users");
    public JButton tabDashboard = new JButton("Dashboard");
    public JButton tabHistory = new JButton("History");

    public JButton exitBtn = new JButton("Exit");

    /* MENU ITEMS */

    public JMenuItem fileNew = new JMenuItem("New User");
    public JMenuItem fileExport = new JMenuItem("Export to CSV");
    public JMenuItem fileBackup = new JMenuItem("Create Backup");
    public JMenuItem fileRestore = new JMenuItem("Restore from Backup");
    public JMenuItem fileExit = new JMenuItem("Exit");

    public JMenuItem toolsStats = new JMenuItem("View Statistics");
    public JMenuItem toolsValidate = new JMenuItem("Validate All Users");
    public JMenuItem toolsClear = new JMenuItem("Clear All Data");

    public JMenuItem helpAbout = new JMenuItem("About");
    public JMenuItem helpGuide = new JMenuItem("User Guide");

    /* HISTORY */

    public JComboBox<String> historyBox = new JComboBox<>();

    /* TABLE */

    public DefaultTableModel tableModel =
            new DefaultTableModel(
                    new String[]{"ID", "Name", "Email", "Class", "DOB", "Phone", "Age"},
                    0);

    public JTable userTable = new JTable(tableModel);

    /* DASHBOARD */

    public JLabel totalUsersLabel = new JLabel("Total Users: 0");
    public JLabel avgAgeLabel = new JLabel("Avg Age: 0");
    public JLabel classStatsLabel = new JLabel("Class Distribution: --");

    public MainView() {

        setTitle("User Management System Pro v2.0");
        setSize(1400, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(240, 245, 250));

        createMenuBar();
        createTopBar();
        createLeftPanel();
        createCenterPanel();
        createTablePanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /* MENU BAR */

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(40, 40, 40));

        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(new Color(240, 240, 240));
        fileMenu.add(fileNew);
        fileMenu.addSeparator();
        fileMenu.add(fileExport);
        fileMenu.add(fileBackup);
        fileMenu.add(fileRestore);
        fileMenu.addSeparator();
        fileMenu.add(fileExit);
        menuBar.add(fileMenu);

        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setForeground(new Color(240, 240, 240));
        toolsMenu.add(toolsStats);
        toolsMenu.add(toolsValidate);
        toolsMenu.addSeparator();
        toolsMenu.add(toolsClear);
        menuBar.add(toolsMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setForeground(new Color(240, 240, 240));
        helpMenu.add(helpGuide);
        helpMenu.add(helpAbout);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    /* TOP BAR */

    private void createTopBar() {

        JPanel top = new JPanel();
        top.setBackground(new Color(30, 144, 255));
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        tabUsers.setBackground(Color.WHITE);
        tabUsers.setFont(new Font("Arial", Font.BOLD, 12));
        tabDashboard.setBackground(Color.WHITE);
        tabDashboard.setFont(new Font("Arial", Font.BOLD, 12));
        tabHistory.setBackground(Color.WHITE);
        tabHistory.setFont(new Font("Arial", Font.BOLD, 12));

        top.add(tabUsers);
        top.add(tabDashboard);
        top.add(tabHistory);

        top.add(new JSeparator(JSeparator.VERTICAL));

        top.add(new JLabel("Search:", JLabel.RIGHT));
        searchTypeCombo.setPreferredSize(new Dimension(80, 25));
        top.add(searchTypeCombo);
        searchValueField.setPreferredSize(new Dimension(120, 25));
        top.add(searchValueField);
        searchBtn.setBackground(new Color(34, 139, 34));
        searchBtn.setForeground(Color.WHITE);
        top.add(searchBtn);
        clearSearchBtn.setBackground(new Color(200, 0, 0));
        clearSearchBtn.setForeground(Color.WHITE);
        top.add(clearSearchBtn);

        top.add(new JSeparator(JSeparator.VERTICAL));

        exportBtn.setBackground(new Color(255, 140, 0));
        exportBtn.setForeground(Color.WHITE);
        top.add(exportBtn);

        refreshBtn.setBackground(new Color(100, 149, 237));
        refreshBtn.setForeground(Color.WHITE);
        top.add(refreshBtn);

        exitBtn.setBackground(new Color(220, 20, 60));
        exitBtn.setForeground(Color.WHITE);
        top.add(Box.createHorizontalGlue());
        top.add(exitBtn);

        add(top, BorderLayout.NORTH);
    }

    /* LEFT PANEL */

    private void createLeftPanel() {

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(6, 1, 10, 10));
        left.setPreferredSize(new Dimension(150, 0));
        left.setBackground(new Color(220, 230, 240));
        left.setBorder(BorderFactory.createTitledBorder("Actions"));

        addBtn.setBackground(new Color(60, 179, 113));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 11));

        updateBtn.setBackground(new Color(255, 215, 0));
        updateBtn.setFont(new Font("Arial", Font.BOLD, 11));

        deleteBtn.setBackground(new Color(255, 99, 71));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 11));

        resetBtn.setBackground(new Color(173, 216, 230));
        resetBtn.setFont(new Font("Arial", Font.BOLD, 11));

        generateBtn.setBackground(new Color(221, 160, 221));
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFont(new Font("Arial", Font.BOLD, 11));

        left.add(addBtn);
        left.add(updateBtn);
        left.add(deleteBtn);
        left.add(resetBtn);
        left.add(generateBtn);
        left.add(Box.createVerticalGlue());

        add(left, BorderLayout.WEST);
    }

    /* CENTER FORM */

    private void createCenterPanel() {

        JPanel form = new JPanel(new GridLayout(8, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("User Details"));
        form.setBackground(new Color(255, 255, 255));

        idField.setFont(new Font("Arial", Font.PLAIN, 11));
        nameField.setFont(new Font("Arial", Font.PLAIN, 11));
        emailField.setFont(new Font("Arial", Font.PLAIN, 11));
        classNameField.setFont(new Font("Arial", Font.PLAIN, 11));
        dobField.setFont(new Font("Arial", Font.PLAIN, 11));
        phoneField.setFont(new Font("Arial", Font.PLAIN, 11));
        addressField.setFont(new Font("Arial", Font.PLAIN, 11));

        JLabel idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(idLabel);
        form.add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(nameLabel);
        form.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(emailLabel);
        form.add(emailField);

        JLabel classLabel = new JLabel("Class");
        classLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(classLabel);
        form.add(classNameField);

        JLabel dobLabel = new JLabel("DOB (dd/MM/yyyy)");
        dobLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(dobLabel);
        form.add(dobField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(phoneLabel);
        form.add(phoneField);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(addressLabel);
        form.add(addressField);

        ageLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
        ageLabel.setForeground(new Color(0, 100, 200));
        form.add(ageLabel);
        form.add(new JLabel(""));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(form, BorderLayout.NORTH);
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(wrapper, BorderLayout.CENTER);
    }

    /* TABLE PANEL */

    private void createTablePanel() {

        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(550, 0));
        right.setBorder(BorderFactory.createTitledBorder("User Database"));
        right.setBackground(Color.WHITE);

        userTable.setRowHeight(25);
        userTable.setFont(new Font("Arial", Font.PLAIN, 11));
        userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(userTable);
        right.add(scrollPane, BorderLayout.CENTER);

        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        statsPanel.setBackground(new Color(240, 248, 255));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalUsersLabel.setFont(new Font("Arial", Font.BOLD, 12));
        avgAgeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        classStatsLabel.setFont(new Font("Arial", Font.BOLD, 11));

        totalUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avgAgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        classStatsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        statsPanel.add(totalUsersLabel);
        statsPanel.add(avgAgeLabel);
        statsPanel.add(classStatsLabel);

        right.add(statsPanel, BorderLayout.SOUTH);

        add(right, BorderLayout.EAST);
    }
}

    /* TOP BAR */

    private void createTopBar() {

        JPanel top = new JPanel();
        top.setBackground(new Color(30, 144, 255));
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        tabUsers.setBackground(Color.WHITE);
        tabUsers.setFont(new Font("Arial", Font.BOLD, 12));
        tabDashboard.setBackground(Color.WHITE);
        tabDashboard.setFont(new Font("Arial", Font.BOLD, 12));
        tabHistory.setBackground(Color.WHITE);
        tabHistory.setFont(new Font("Arial", Font.BOLD, 12));

        top.add(tabUsers);
        top.add(tabDashboard);
        top.add(tabHistory);

        top.add(new JSeparator(JSeparator.VERTICAL));

        top.add(new JLabel("Search:", JLabel.RIGHT));
        searchTypeCombo.setPreferredSize(new Dimension(80, 25));
        top.add(searchTypeCombo);
        searchValueField.setPreferredSize(new Dimension(120, 25));
        top.add(searchValueField);
        searchBtn.setBackground(new Color(34, 139, 34));
        searchBtn.setForeground(Color.WHITE);
        top.add(searchBtn);
        clearSearchBtn.setBackground(new Color(200, 0, 0));
        clearSearchBtn.setForeground(Color.WHITE);
        top.add(clearSearchBtn);

        top.add(new JSeparator(JSeparator.VERTICAL));

        exportBtn.setBackground(new Color(255, 140, 0));
        exportBtn.setForeground(Color.WHITE);
        top.add(exportBtn);

        refreshBtn.setBackground(new Color(100, 149, 237));
        refreshBtn.setForeground(Color.WHITE);
        top.add(refreshBtn);

        exitBtn.setBackground(new Color(220, 20, 60));
        exitBtn.setForeground(Color.WHITE);
        top.add(Box.createHorizontalGlue());
        top.add(exitBtn);

        add(top, BorderLayout.NORTH);
    }

    /* LEFT PANEL */

    private void createLeftPanel() {

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(6, 1, 10, 10));
        left.setPreferredSize(new Dimension(150, 0));
        left.setBackground(new Color(220, 230, 240));
        left.setBorder(BorderFactory.createTitledBorder("Actions"));

        addBtn.setBackground(new Color(60, 179, 113));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 11));

        updateBtn.setBackground(new Color(255, 215, 0));
        updateBtn.setFont(new Font("Arial", Font.BOLD, 11));

        deleteBtn.setBackground(new Color(255, 99, 71));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 11));

        resetBtn.setBackground(new Color(173, 216, 230));
        resetBtn.setFont(new Font("Arial", Font.BOLD, 11));

        generateBtn.setBackground(new Color(221, 160, 221));
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFont(new Font("Arial", Font.BOLD, 11));

        left.add(addBtn);
        left.add(updateBtn);
        left.add(deleteBtn);
        left.add(resetBtn);
        left.add(generateBtn);
        left.add(Box.createVerticalGlue());

        add(left, BorderLayout.WEST);
    }

    /* CENTER FORM */

    private void createCenterPanel() {

        JPanel form = new JPanel(new GridLayout(8, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("User Details"));
        form.setBackground(new Color(255, 255, 255));

        idField.setFont(new Font("Arial", Font.PLAIN, 11));
        nameField.setFont(new Font("Arial", Font.PLAIN, 11));
        emailField.setFont(new Font("Arial", Font.PLAIN, 11));
        classNameField.setFont(new Font("Arial", Font.PLAIN, 11));
        dobField.setFont(new Font("Arial", Font.PLAIN, 11));
        phoneField.setFont(new Font("Arial", Font.PLAIN, 11));
        addressField.setFont(new Font("Arial", Font.PLAIN, 11));

        JLabel idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(idLabel);
        form.add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(nameLabel);
        form.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(emailLabel);
        form.add(emailField);

        JLabel classLabel = new JLabel("Class");
        classLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(classLabel);
        form.add(classNameField);

        JLabel dobLabel = new JLabel("DOB (dd/MM/yyyy)");
        dobLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(dobLabel);
        form.add(dobField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(phoneLabel);
        form.add(phoneField);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 11));
        form.add(addressLabel);
        form.add(addressField);

        ageLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
        ageLabel.setForeground(new Color(0, 100, 200));
        form.add(ageLabel);
        form.add(new JLabel(""));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(form, BorderLayout.NORTH);
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(wrapper, BorderLayout.CENTER);
    }

    /* TABLE PANEL */

    private void createTablePanel() {

        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(550, 0));
        right.setBorder(BorderFactory.createTitledBorder("User Database"));
        right.setBackground(Color.WHITE);

        userTable.setRowHeight(25);
        userTable.setFont(new Font("Arial", Font.PLAIN, 11));
        userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(userTable);
        right.add(scrollPane, BorderLayout.CENTER);

        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        statsPanel.setBackground(new Color(240, 248, 255));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalUsersLabel.setFont(new Font("Arial", Font.BOLD, 12));
        avgAgeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        classStatsLabel.setFont(new Font("Arial", Font.BOLD, 11));

        totalUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avgAgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        classStatsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        statsPanel.add(totalUsersLabel);
        statsPanel.add(avgAgeLabel);
        statsPanel.add(classStatsLabel);

        right.add(statsPanel, BorderLayout.SOUTH);

        add(right, BorderLayout.EAST);
    }
}