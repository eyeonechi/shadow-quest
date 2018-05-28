/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.Graphics;

/**
 * Speech Interface
 */
public interface Speech {
	/**
	 * Object speaks
	 *
	 * @param graphics
	 *            : Used for rendering
	 * @param player
	 *            : player which the object talks to
	 */
	public default void speak(Graphics graphics, Player player) {
	}
}
