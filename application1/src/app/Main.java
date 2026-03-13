package app;

import view.MainView;
import model.UserModel;
import controller.MainController;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {

    Node head;

    void pushHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("NULL");
    }
}

public class Main {

    public static void main(String[] args) {

        // MVC PART
        MainView view = new MainView();
        UserModel model = new UserModel();
        new MainController(view, model);

        // PUSH HEAD PART
        LinkedList list = new LinkedList();

        list.pushHead(10);
        list.pushHead(20);
        list.pushHead(30);

        list.display();
    }
}