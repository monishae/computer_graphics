package com.utd.cg.asgn4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class L_Systems_Tree extends JPanel {

    private static final long serialVersionUID = 1L;

    public L_Systems_Tree() {
        
    }

    private void drawTree(Graphics g, int x1, int y1, double angle, int depth,int width) {
        if (depth == 0)
            return;
        
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 12.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 12.0);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawLine(x1, y1, x2, y2);
        
        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        drawTree(g2d, x2, y2, angle - 20, depth - 1,width-1);
        drawTree(g2d, x2, y2, angle + 20, depth - 1,width -1 );
    }

    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g.create();
    	g2d.setColor(Color.DARK_GRAY);
        drawTree(g2d, getWidth() / 2, getHeight(), -90, 5,50);
    }

    public static void main(String... args) {
        final JFrame frame = new JFrame("L Systems - Tree Fractal");
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setContentPane(new L_Systems_Tree());
                frame.setSize(1000, 700);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(true);
                frame.setVisible(true);
            }
        });
    }
}