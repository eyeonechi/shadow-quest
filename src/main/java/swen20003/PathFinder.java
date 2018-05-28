/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.util.pathfinding.AStarHeuristic;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 * PathFinder Class
 */
public class PathFinder implements AStarHeuristic {
	private final int MAXPATH = 500;
	private AStarPathFinder pathFinder;
	private Path path;
	private Map map;

	/**
	 * Creates a new PathFinder object
	 *
	 * @param map
	 */
	public PathFinder(Map map) {
		this.pathFinder = new AStarPathFinder(map, MAXPATH, true);
		this.map = map;
	}

	/**
	 * Returns the path
	 *
	 * @return
	 */
	public Path getPath() {
		return this.path;
	}

	/**
	 * Searches for a path
	 *
	 * @param player
	 *            : player traversing
	 * @param xDest
	 *            : x-destination
	 * @param yDest
	 *            : y-destination
	 * @return
	 */
	public boolean search(Player player, int xDest, int yDest) {
		if (this.map.blocked(null, xDest, yDest)) {
			this.path = this.pathFinder.findPath(player,
					map.getTileIndexX(player.getX()),
					map.getTileIndexY(player.getY()), map.getTileIndexX(xDest),
					map.getTileIndexY(yDest));
			if (this.path == null) {
				return false;
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.newdawn.slick.util.pathfinding.AStarHeuristic#getCost(org.newdawn
	 * .slick.util.pathfinding.TileBasedMap,
	 * org.newdawn.slick.util.pathfinding.Mover, int, int, int, int)
	 */
	@Override
	public float getCost(TileBasedMap map, Mover player, int xSrc, int ySrc,
			int xDest, int yDest) {
		double cost = Math.sqrt((xDest - xSrc) * (xDest - xSrc)
				+ (yDest - ySrc) * (yDest - ySrc));
		System.out.println(cost);
		return (float) cost;
	}
}
