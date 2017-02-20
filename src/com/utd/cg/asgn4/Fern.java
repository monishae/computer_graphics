package com.utd.cg.asgn4;

import java.awt.Point;

public class Fern implements Fractal {
	public Point getStartingPoint() {
		return new Point(290, 500);
	}
	
	public static final double INITIAL_DIST = 40.0;
	
	public static final double MIN_DIST = 1.0;
	
	/** Factor by which "dist" should be reduced when starting a new "branch" sub-fern. */
	public static final double BRANCH_FACTOR = .40;
	
	/** Factor by which "dist" should be reduced when continuing the "spine" of a fern. */
	public static final double SPINE_FACTOR = .8;
	
	/** How to turn when starting the left "branch" sub-fern. */
	public static final double BRANCH_LEFT = -(Math.PI / 3); 
	
	/** How to turn when starting the right "branch" sub-fern. */
	public static final double BRANCH_RIGHT = Math.PI / 3;

	/** The "spine" of the fern may curve to the left. */
	public static final double CURVE_LEFT = -.10;

	/** The "spine" of the fern may curve to the right. */
	private static final double CURVE_RIGHT = .10;

	public void draw(Turtle turtle) {
		// The spine of the main fern curves to the right.
		drawFern(turtle, INITIAL_DIST, CURVE_RIGHT);
	}
	
	private void drawFern(Turtle turtle, double dist, double curveOfSpine) {
		// Hints:
		// 1. Don't continue if dist < MIN_DIST
		// 2. Move, draw the right branch sub-fern, move, draw
		//    the left branch sub-fern, move, turn by curveOfSpine,
		//    then draw the spine sub-fern.
		// 3. The constants BRANCH_LEFT and BRANCH_RIGHT specify how
		//    to turn when creating the left and right branch sub-ferns.
		// 4. The right branch sub-fern should curve right, and the left branch
		//    sub-fern should curve left.
		// 5. When drawing the left and right branch sub-ferns,
		//    multiply dist by BRANCH_FACTOR.
		// 6. When drawing the spine continuation sub-fern, multiply dist
		//    by SPINE_FACTOR.
	}

	public String toString() {
		return "Fern";
	}

}
