package com.utd.cg.asgn4;

//FractalGrammars.java
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class FractalGrammars extends Frame {
	public static void main(String[] args) {
		new FractalGrammars("Tree2.txt");
	}

	FractalGrammars(String fileName) {
		super("Click left or right mouse button to change the level");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setSize(900, 700);
		setLocation(200, 200);
		add("Center", new CvFractalGrammars(fileName));
		show();
	}
}

class CvFractalGrammars extends Canvas {
	String fileName, axiom, strF, strf, strX, strY;
	int maxX, maxY, level = 1;
	double xLast, yLast, dir, rotation, dirStart, fxStart, fyStart, lengthFract, reductFact;

	void error(String str) {
		System.out.println(str);
		System.exit(1);
	}

	CvFractalGrammars(String fileName) {
		Input inp = new Input(fileName);
		if (inp.fails())
			error("Cannot open input file.");
		axiom = inp.readString();
		inp.skipRest();
		strF = inp.readString();
		inp.skipRest();
		strf = inp.readString();
		inp.skipRest();
		strX = inp.readString();
		inp.skipRest();
		strY = inp.readString();
		inp.skipRest();
		rotation = inp.readFloat();
		inp.skipRest();
		dirStart = inp.readFloat();
		inp.skipRest();
		fxStart = inp.readFloat();
		inp.skipRest();
		fyStart = inp.readFloat();
		inp.skipRest();
		lengthFract = inp.readFloat();
		inp.skipRest();
		reductFact = inp.readFloat();
		if (inp.fails())
			error("Input file incorrect.");
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
					level--; // Right mouse button decreases level
					if (level < 1)
						level = 1;
				} else
					level++; // Left mouse button increases level
				repaint();
			}
		});
	}

	Graphics g;

	int iX(double x) {
		return (int) Math.round(x);
	}

	int iY(double y) {
		return (int) Math.round(maxY - y);
	}

	int i = 50;

	   void drawTo(Graphics g, double x, double y)
	   {  
		  
		   for(int i=iY(y);i<iY(yLast);i++){
			   //System.out.println("Entered");
			   
			   for(int j=0;j<6;j+=(0.6*i*lengthFract/6)){
				   g.drawRect(iX(x), i, iX((0.5*i*lengthFract)/6), 1);
			   }
				      
		   }
		   
	   }

	void moveTo(Graphics g, double x, double y)

	{
		xLast = x;
		yLast = y;
	}

	// int width=4;

	public void paint(Graphics g) {
		Dimension d = getSize();
		maxX = d.width - 1;
		maxY = d.height - 1;
		xLast = fxStart * maxX; // starting at the centre
		yLast = fyStart * maxY;
		dir = dirStart; // Initial direction in degrees pointing upward
		turtleGraphics(g, axiom, level, lengthFract * maxY);
	}

	public void turtleGraphics(Graphics g, String instruction, int depth, double len) {
		double xMark = 0, yMark = 0, dirMark = 0;
		for (int i = 0; i < instruction.length(); i++) {
			char ch = instruction.charAt(i);

			switch (ch) {
			case 'F': // Step forward and draw
				// Start: (xLast, yLast), direction: dir, steplength: len

				if (depth == 0) {
					double rad = Math.PI / 180 * dir, // Degrees -> radians
							dx = len * Math.cos(rad), dy = len * Math.sin(rad);
					drawTo(g, xLast + dx, yLast + dy);

					if (i == 5) {

						g.setColor(Color.GREEN);
						g.fillOval(iX(xLast) - 10, iY(yLast) - 3, 20, 6); // coordinate
																			// and
																			// rotation
																			// of
																			// the
																			// drawing
																			// fractal
																			// tree
						g.setColor(Color.BLACK);
					}

				}

				else
					turtleGraphics(g, strF, depth - 1, reductFact * len);
				break;
			case 'f': // Step forward without drawing
				// Start: (xLast, yLast), direction: dir, steplength: len
				if (depth == 0) {
					double rad = Math.PI / 180 * dir, // Degrees -> radians
							dx = len * Math.cos(rad), dy = len * Math.sin(rad);
					moveTo(g, xLast + dx, yLast + dy);
				} else
					turtleGraphics(g, strf, depth - 1, reductFact * len);
				break;
			case 'X':
				if (depth > 0)
					turtleGraphics(g, strX, depth - 1, reductFact * len);
				break;
			case 'Y':
				if (depth > 0)
					turtleGraphics(g, strY, depth - 1, reductFact * len);
				break;
			case '+': // Turn right
				dir -= rotation;
				break;
			case '-': // Turn left
				dir += rotation;
				break;
			case '[': // Save position and direction
				xMark = xLast;
				yMark = yLast;
				dirMark = dir;
				break;
			case ']': // Back to saved position and direction
				xLast = xMark;
				yLast = yMark;
				dir = dirMark;
				break;
			}
		}
	}
}
