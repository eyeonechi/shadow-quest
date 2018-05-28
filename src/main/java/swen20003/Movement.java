/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.util.pathfinding.Path;

/**
 * Movement Interface
 */
public interface Movement {
	/**
	 * Object chases an entity
	 *
	 * @param entity
	 *            : entity to be chased
	 * @param delta
	 *            : time (in milliseconds) since the last frame update
	 */
	public default void chase(Entity entity, int delta) {
	}

	/**
	 * Object flees from an entity
	 *
	 * @param entity
	 *            : entity to be chased
	 * @param delta
	 *            : time (in milliseconds) since the last frame update
	 */
	public default void flee(Entity entity, int delta) {
	}

	/**
	 * Object traverses to destination coordinates
	 *
	 * @param map
	 *            : map
	 * @param path
	 *            : path to destination
	 * @param delta
	 *            : time (in milliseconds) since the last frame update
	 * @return
	 */
	public default boolean traverse(Map map, Path path, int delta) {
		return false;
	}

	/**
	 * Object walks
	 *
	 * @param map
	 *            : map
	 * @param x
	 *            : x-direction
	 * @param y
	 *            : y-direction
	 */
	public default void walk(Map map, double x, double y) {
	}

	/**
	 * Object walks left
	 *
	 * @param map
	 *            : map
	 * @param x
	 *            : x-direction
	 */
	public default void walkLeft(Map map, double x) {
	}

	/**
	 * Object walks right
	 *
	 * @param map
	 *            : map
	 * @param x
	 *            : x-direction
	 */
	public default void walkRight(Map map, double x) {
	}

	/**
	 * Object walks up
	 *
	 * @param map
	 *            : map
	 * @param y
	 *            : y-direction
	 */
	public default void walkUp(Map map, double y) {
	}

	/**
	 * Object walks down
	 *
	 * @param map
	 *            : map
	 * @param y
	 *            : y-direction
	 */
	public default void walkDown(Map map, double y) {
	}

	/**
	 * Object wanders around
	 *
	 * @param map
	 *            : map
	 * @param delta
	 *            : time (in milliseconds) since the last frame update
	 */
	public default void wander(Map map, int delta) {
	}
}
