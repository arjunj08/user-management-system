package controller;

import javax.swing.*;
import java.awt.*;

public class ChartPanel extends JPanel {

    private int value=0;

    public void setValue(int v){
        value=v;
        repaint();
    }

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2=(Graphics2D)g;

        g2.setColor(new Color(52,152,219));

        g2.fillRect(50,200-value*10,80,value*10);

        g2.setColor(Color.BLACK);

        g2.drawString("Users",60,220);
    }
}