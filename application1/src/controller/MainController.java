package controller;

import view.MainView;
import model.UserModel;

import javax.swing.*;
import java.util.Random;

public class MainController {

    private MainView view;
    private UserModel model;

    public MainController(MainView view, UserModel model){

        this.view = view;
        this.model = model;

        view.signUpBtn.addActionListener(e -> signUpUser());
        view.addBtn.addActionListener(e -> addUser());
        view.updateBtn.addActionListener(e -> updateUser());
        view.deleteBtn.addActionListener(e -> deleteUser());
        view.resetBtn.addActionListener(e -> resetForm());
        view.generateBtn.addActionListener(e -> generateID());

        view.searchField.addActionListener(e -> searchUser());
        view.historyBox.addActionListener(e -> historySelect());

        view.tabDashboard.addActionListener(e -> showDashboard());

        // FIXED EXIT BUTTON
        view.exitBtn.addActionListener(e -> { System.exit(0); });
    }

    private void signUpUser(){

        String id = view.idField.getText();
        String name = view.nameField.getText();
        String cls = view.classField.getText();
        String dob = view.dobField.getText();

        if(id.isEmpty() || name.isEmpty() || cls.isEmpty() || dob.isEmpty()){
            JOptionPane.showMessageDialog(view,"Fill all fields");
            return;
        }

        if(model.exists(id)){
            JOptionPane.showMessageDialog(view,"User already exists");
            return;
        }

        model.addUser(id,name,cls,dob);

        refreshTable();

        JOptionPane.showMessageDialog(view,"User Signed Up Successfully");

        resetForm();
    }

    private void addUser(){
        signUpUser();
    }

    private void updateUser(){

        String id = view.idField.getText();

        if(!model.exists(id)){
            JOptionPane.showMessageDialog(view,"User not found");
            return;
        }

        model.updateUser(
                id,
                view.nameField.getText(),
                view.classField.getText(),
                view.dobField.getText()
        );

        refreshTable();
    }

    private void deleteUser(){

        String id = view.idField.getText();

        if(!model.exists(id)){
            JOptionPane.showMessageDialog(view,"User not found");
            return;
        }

        model.deleteUser(id);

        refreshTable();

        resetForm();
    }

    private void searchUser(){

        String id = view.searchField.getText();

        if(model.exists(id)){

            String[] u = model.getUser(id);

            view.idField.setText(id);
            view.nameField.setText(u[0]);
            view.classField.setText(u[1]);
            view.dobField.setText(u[2]);

            view.historyBox.addItem(id);

        }else{

            JOptionPane.showMessageDialog(view,"User not found");
        }
    }

    private void historySelect(){

        String id = (String) view.historyBox.getSelectedItem();

        if(id != null && model.exists(id)){

            String[] u = model.getUser(id);

            view.idField.setText(id);
            view.nameField.setText(u[0]);
            view.classField.setText(u[1]);
            view.dobField.setText(u[2]);
        }
    }

    private void generateID(){

        int id = new Random().nextInt(1000);

        view.idField.setText(String.valueOf(id));
    }

    private void resetForm(){

        view.idField.setText("");
        view.nameField.setText("");
        view.classField.setText("");
        view.dobField.setText("");
    }

    private void refreshTable(){

        view.tableModel.setRowCount(0);

        model.getUsers().forEach((id,u) -> {

            view.tableModel.addRow(
                    new Object[]{id,u[0],u[1],u[2]}
            );
        });

        view.totalUsersLabel.setText("Total Users : " + model.totalUsers());
    }

    private void showDashboard(){

        JOptionPane.showMessageDialog(view,
                "Dashboard\nTotal Users : " + model.totalUsers());
    }
}