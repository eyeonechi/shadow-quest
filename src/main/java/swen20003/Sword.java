/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Sword Class
 */
public class Sword extends Item {
	private final static String IMAGE = "assets/items/sword.png";
	private final static String NAME = "Sword";

	/**
	 * Creates a new sword object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Sword(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Item#enhance(src.Unit)
	 */
	@Override
	public void enhance(Unit unit) {
		this.enhanceDamage(unit);
	}
}
