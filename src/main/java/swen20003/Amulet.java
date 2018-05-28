/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Amulet class
 */
public class Amulet extends Item {
	private final static String IMAGE = "assets/items/amulet.png";
	private final static String NAME = "Amulet";

	/**
	 * Creates a new Amulet object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Amulet(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Item#enhance(src.Unit)
	 */
	@Override
	public void enhance(Unit unit) {
		this.enhanceVitality(unit);
	}
}
