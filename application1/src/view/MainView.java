package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JFrame {

    /* TEXT FIELDS */

    public JTextField idField = new JTextField();
    public JTextField nameField = new JTextField();
    public JTextField classNameField = new JTextField();
    public JTextField dobField = new JTextField();

    public JTextField searchField = new JTextField(10);

    /* BUTTONS */

    public JButton addBtn = new JButton("Add");
    public JButton updateBtn = new JButton("Update");
    public JButton deleteBtn = new JButton("Delete");
    public JButton resetBtn = new JButton("Reset");
    public JButton generateBtn = new JButton("Generate ID");

    public JButton signUpBtn = new JButton("Sign Up");

    public JButton tabUsers = new JButton("Users");
    public JButton tabDashboard = new JButton("Dashboard");

    public JButton exitBtn = new JButton("Exit");

    /* HISTORY */

    public JComboBox<String> historyBox = new JComboBox<>();

    /* TABLE */

    public DefaultTableModel tableModel =
            new DefaultTableModel(
                    new String[]{"ID","Name","Class","DOB"},0);

    public JTable userTable = new JTable(tableModel);

    /* DASHBOARD */

    public JLabel totalUsersLabel = new JLabel("Total Users : 0");

    public MainView(){

        setTitle("User Management System");
        setSize(1100,650);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(240,245,250));

        createTopBar();
        createLeftPanel();
        createCenterPanel();
        createTablePanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* TOP BAR */

    private void createTopBar(){

        JPanel top = new JPanel();

        top.setBackground(new Color(30,144,255));

        tabUsers.setBackground(Color.WHITE);
        tabDashboard.setBackground(Color.WHITE);

        top.add(tabUsers);
        top.add(tabDashboard);

        top.add(new JLabel("Search"));
        top.add(searchField);

        top.add(new JLabel("History"));
        top.add(historyBox);

        exitBtn.setBackground(Color.RED);
        exitBtn.setForeground(Color.WHITE);

        top.add(exitBtn);

        add(top,BorderLayout.NORTH);
    }

    /* LEFT PANEL */

    private void createLeftPanel(){

        JPanel left = new JPanel();

        left.setLayout(new GridLayout(5,1,10,10));
        left.setPreferredSize(new Dimension(200,0));
        left.setBackground(new Color(220,230,240));

        addBtn.setBackground(new Color(60,179,113));
        updateBtn.setBackground(new Color(255,215,0));
        deleteBtn.setBackground(new Color(255,99,71));
        resetBtn.setBackground(new Color(173,216,230));
        generateBtn.setBackground(new Color(221,160,221));

        left.add(addBtn);
        left.add(updateBtn);
        left.add(deleteBtn);
        left.add(resetBtn);
        left.add(generateBtn);

        add(left,BorderLayout.WEST);
    }

    /* CENTER FORM */

    private void createCenterPanel(){

        JPanel form = new JPanel(new GridLayout(5,2,10,10));

        form.setBorder(BorderFactory.createTitledBorder("User Form"));
        form.setBackground(Color.WHITE);

        form.add(new JLabel("ID"));
        form.add(idField);

        form.add(new JLabel("Name"));
        form.add(nameField);

        form.add(new JLabel("Class"));
        form.add(classNameField);

        form.add(new JLabel("DOB"));
        form.add(dobField);

        signUpBtn.setBackground(new Color(100,149,237));
        signUpBtn.setForeground(Color.WHITE);

        form.add(signUpBtn);
        form.add(new JLabel(""));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(form, BorderLayout.NORTH);

        add(wrapper,BorderLayout.CENTER);
    }

    /* TABLE PANEL */

    private void createTablePanel(){

        JPanel right = new JPanel(new BorderLayout());

        right.setPreferredSize(new Dimension(450,0));

        right.setBorder(BorderFactory.createTitledBorder("User Database"));

        userTable.setRowHeight(25);

        right.add(new JScrollPane(userTable),BorderLayout.CENTER);

        totalUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        right.add(totalUsersLabel,BorderLayout.SOUTH);

        add(right,BorderLayout.EAST);
    }
}