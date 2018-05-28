/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

/**
 * Interaction Interface
 */
public interface Interaction {
	public static final int AMULETPOS = 0, TOMEPOS = 1, ELIXIRPOS = 2,
			SWORDPOS = 3;

	/**
	 * @param item
	 *            : item to be picked up
	 */
	public default void pickItem(Item item) {
	}

	/**
	 * @param item
	 *            : item to be used
	 */
	public default void useItem(Item item) {
	}
}
