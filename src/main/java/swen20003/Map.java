/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 * Map Class
 */
public class Map implements TileBasedMap {
	private final static int SCREENTILEWIDTH = 13, SCREENTILEHEIGHT = 10;
	private final static String MAP = "assets/map.tmx", TILE = "assets";
	public final static String ACCESSIBLE = "0";
	public final static String PROPERTY = "block";
	protected TiledMap map;

	/**
	 * Creates a new Map object
	 *
	 * @throws SlickException
	 */
	public Map() throws SlickException {
		this.map = new TiledMap(MAP, TILE);
	}

	/**
	 * @return
	 */
	public int getTileX() {
		return this.map.getTileWidth();
	}

	/**
	 * @return
	 */
	public int getTileY() {
		return this.map.getTileHeight();
	}

	/**
	 * @return
	 */
	public int getTileNumX() {
		return this.map.getWidth();
	}

	/**
	 * @return
	 */
	public int getTileNumY() {
		return this.map.getHeight();
	}

	/**
	 * @return
	 */
	public int getMapX() {
		return this.getTileX() * this.getTileNumX();
	}

	/**
	 * @return
	 */
	public int getMapY() {
		return this.getTileY() * this.getTileNumY();
	}

	/**
	 * @param x
	 *            : x-coordinate
	 * @return
	 */
	public int getTileIndexX(double x) {
		return (int) x / this.getTileX();
	}

	/**
	 * @param y
	 *            : y-coordinate
	 * @return
	 */
	public int getTileIndexY(double y) {
		return (int) y / this.getTileY();
	}

	/**
	 * @param x
	 *            : x-coordinate
	 * @return
	 */
	public int getTileOffsetX(double x) {
		return -(int) (x % this.getTileX());
	}

	/**
	 * @param y
	 *            : y-coordinate
	 * @return
	 */
	public int getTileOffsetY(double y) {
		return -(int) (y % this.getTileY());
	}

	/**
	 * @param nextTileIndex
	 *            : next tile x-index
	 * @param ytileIndex
	 *            : current y-index
	 * @return
	 */
	public boolean getTilePropertyX(int nextTileIndex, int ytileIndex) {
		if (nextTileIndex > 0 && nextTileIndex < this.getTileNumX()) {
			int tileID = this.map.getTileId(nextTileIndex, ytileIndex, 0);
			String tileProp = map.getTileProperty(tileID, PROPERTY, ACCESSIBLE);
			return (tileProp == ACCESSIBLE) ? true : false;
		}
		return false;
	}

	/**
	 * @param xTileIndex
	 *            : current x-index
	 * @param nextTileIndex
	 *            : next tile y-index
	 * @return
	 */
	public boolean getTilePropertyY(int xTileIndex, int nextTileIndex) {
		if (nextTileIndex > 0 && nextTileIndex < this.getTileNumY()) {
			int tileID = this.map.getTileId(xTileIndex, nextTileIndex, 0);
			String tileProp = map.getTileProperty(tileID, PROPERTY, ACCESSIBLE);
			return (tileProp == ACCESSIBLE) ? true : false;
		}
		return false;
	}

	/**
	 * Renders the map at a particular location
	 *
	 * @param x
	 *            : Camera x-coordinate
	 * @param y
	 *            : Camera y-coordinate
	 */
	public void render(double x, double y) {
		map.render(this.getTileOffsetX(x), this.getTileOffsetY(y),
				this.getTileIndexX(x), this.getTileIndexY(y), SCREENTILEWIDTH,
				SCREENTILEHEIGHT);
	}

	// TileBasedMap Interface Methods
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.newdawn.slick.util.pathfinding.TileBasedMap#blocked(org.newdawn.slick
	 * .util.pathfinding.PathFindingContext, int, int)
	 */
	@Override
	public boolean blocked(PathFindingContext context, int x, int y) {
		return this.map.getTileProperty(this.map.getTileId(
				this.getTileIndexX(x), this.getTileIndexY(y), 0), PROPERTY,
				ACCESSIBLE) == ACCESSIBLE;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.newdawn.slick.util.pathfinding.TileBasedMap#getCost(org.newdawn.slick
	 * .util.pathfinding.PathFindingContext, int, int)
	 */
	@Override
	public float getCost(PathFindingContext context, int x, int y) {
		double cost = Math.sqrt((context.getSourceX() - x)
				* (context.getSourceX() - x) + (context.getSourceY() - y)
				* (context.getSourceY() - y));
		return (float) cost;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.newdawn.slick.util.pathfinding.TileBasedMap#getHeightInTiles()
	 */
	@Override
	public int getHeightInTiles() {
		return this.getTileNumY();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.newdawn.slick.util.pathfinding.TileBasedMap#getWidthInTiles()
	 */
	@Override
	public int getWidthInTiles() {
		return this.getTileNumX();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.newdawn.slick.util.pathfinding.TileBasedMap#pathFinderVisited(int,
	 * int)
	 */
	@Override
	public void pathFinderVisited(int x, int y) {
	}
}
