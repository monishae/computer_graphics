package com.utd.cg.asgn4;

public interface Turtle {
	/**
	 * Turn the Turtle in the given direction (by adding it to
	 * the Turtle's current orientation.)  The direction is given
	 * in radians: positive means turn clockwise, negative means
	 * turn counter-clockwise.
	 * 
	 * @param direction the direction and amount to turn
	 */
	public void turn(double direction);
	
	/**
	 * Move the Turtle straight ahead by given distance according
	 * to its current orientation (what direction it is facing in).
	 * 
	 * @param distance the distance to move
	 */
	public void move(double distance);
	
	/**
	 * Create an exact duplicate of this Turtle.
	 * The new Turtle will have the same location and orientation,
	 * but may be turned and moved independently.
	 * 
	 * @return an exact duplicate of this Turtle
	 */
	public Turtle branch();
}
