/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Elixir Class
 */
public class Elixir extends Item {
	private final static String IMAGE = "assets/items/elixir.png";
	private final static String NAME = "Elixir";

	/**
	 * Creates a new Elixir object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Elixir(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
	}
}
