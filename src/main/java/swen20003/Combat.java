/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

/**
 * Combat Interface
 */
public interface Combat {
	/**
	 * Unit attacks another unit
	 *
	 * @param unit
	 *            : The unit being attacked
	 * @param delta
	 *            : Time (in milliseconds) passed since the last frame
	 */
	public void attack(Unit unit, int delta);
}
