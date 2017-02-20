package com.utd.cg.asgn4;

import java.awt.Color;

import java.awt.Graphics;
import javax.swing.JFrame;
 
public class curves extends JFrame {
 
	private static final long serialVersionUID = 1L;
	
	
	public curves() {
        super("Fractal Tree");
        setSize (700, 500);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

 /*Description of parameters used:
  * drawTree(Graphics g, double x0, double y0, double len, double angle,double th)  
 * x0,y0 - start point of each branch;
 * len - chosen length of a branch(which varies by 60% and 75% for consecutive branches).
 * angle - angle of inclination of each branch
 * th- thickness of each branch(changes by a factor of 0.87)
 * */
  	public void drawTree(Graphics g, double x0, double y0, double len, double angle,double th) {
       
        if(len > 1) {
        	g.setColor(Color.black);
            double x1 = x0 + len * (Math.cos(Math.toRadians(angle)));
            double y1 = y0 - len * (Math.sin(Math.toRadians(angle)));
            
            int[] xPoints = new int[4];
            int[] yPoints = new int[4];
       
        	 
          xPoints[0] =  (int) x0;
          xPoints[3] = (int) x1; 
          xPoints[1] =  (int) ((int)x0+th);
          xPoints[2] = (int) ((int) x1+th);
          yPoints[0] = (int) y0;
          yPoints[3] =  (int)y1;
          yPoints[1] = (int) ((int) y0);
          yPoints[2] = (int) ((int) y1);
      
        	
          g.fillPolygon(xPoints, yPoints, 4);
          
      
          drawTree(g,x1, y1, len * 0.66, angle - 50,th*0.87*0.87);  
          drawTree(g,x1, y1, len * 0.75, angle + 30,th*0.87);
            th=th*0.87;
            
         
        }
        else
        {	g.setColor(Color.GREEN);
        	g.fillRect((int)x0, (int)y0, 1,(int)2);
        }
    
        }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        
        drawTree(g,400, 400, 100, 80,10);
    }
 
    public static void main(String[] args) {
        new curves().setVisible(true);
    }
}