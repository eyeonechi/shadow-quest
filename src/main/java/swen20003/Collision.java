/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.Graphics;

/**
 * Collision Interface
 */
public interface Collision {
	public final static int COLLIDERANGE = 50, DETECTRANGE = 150;

	/**
	 * Object within range of another entity
	 *
	 * @param entity
	 *            : entity collided with
	 * @return
	 */
	public default boolean collide(Entity entity) {
		return false;
	}

	/**
	 * Object 'sees' another entity
	 *
	 * @param entity
	 *            : entity seen
	 * @return
	 */
	public default boolean detect(Entity entity) {
		return false;
	}

	/**
	 * Object switches orientation to face a player entity
	 *
	 * @param graphics
	 *            : Used for rendering
	 * @param player
	 *            : player being noticed
	 * @param talk
	 *            : talk toggle
	 */
	public default void notice(Graphics graphics, Player player, boolean talk) {
	}
}
