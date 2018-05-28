/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Abstract Item Class
 */
public abstract class Item extends Entity implements Support {
	/**
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @param image
	 *            : image path
	 * @param name
	 *            : name of item
	 * @throws SlickException
	 */
	public Item(double x, double y, String image, String name)
			throws SlickException {
		super(x, y, image, name);
	}

	/**
	 * Enhances a unit
	 *
	 * @param unit
	 *            : unit to enhance
	 */
	public void enhance(Unit unit) {
	}

	/**
	 * Renders the item
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 */
	public void render(double x, double y) {
		this.image.draw((float) x, (float) y);
	}

	/**
	 * Removes an item from the world
	 */
	public void remove() {
		this.x = 0;
		this.y = 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
}
