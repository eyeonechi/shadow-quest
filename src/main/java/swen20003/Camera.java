/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Represents the camera that controls the user's viewpoint
 */
public class Camera {
	private static int XMAP, YMAP;
	private static int XSCREEN, YSCREEN;

	private double x, y;

	/**
	 * Create a new Camera object
	 *
	 * @param xScreen
	 *            : Screen width
	 * @param yScreen
	 *            : Screen height
	 * @param xMap
	 *            : Map width
	 * @param yMap
	 *            : Map height
	 */
	public Camera(int xScreen, int yScreen, int xMap, int yMap) {
		XSCREEN = xScreen;
		YSCREEN = yScreen;
		XMAP = xMap;
		YMAP = yMap;
	}

	/**
	 * Update the game camera to re-centre it's viewpoint around an object
	 *
	 * @param x
	 *            : New x-position of the object in focus
	 * @param y
	 *            : New y-position of the object in focus
	 * @throws SlickException
	 */
	private void update(double x, double y) throws SlickException {
		if (this.x < 0) {
			this.x = 0;
		} else if ((this.x + XSCREEN) > XMAP) {
			this.x = XMAP - XSCREEN;
		}
		if (this.y < 0) {
			this.y = 0;
		} else if ((this.y + YSCREEN) > YMAP) {
			this.y = YMAP - YSCREEN;
		}
	}

	/**
	 * Tells the camera to follow a given unit
	 *
	 * @param entity
	 *            : The object being followed
	 * @throws SlickException
	 */
	public void follow(Entity entity) throws SlickException {
		this.x = entity.getX() - (XSCREEN / 2);
		this.y = entity.getY() - (YSCREEN / 2);
		this.update(this.x, this.y);
	}

	/**
	 * Returns the maximum x value on screen
	 *
	 * @return
	 */
	public int getMaxX() {
		return (int) (this.x + (XSCREEN / 2));
	}

	/**
	 * Returns the maximum y value on screen
	 *
	 * @return
	 */
	public int getMaxY() {
		return (int) (this.y + (YSCREEN / 2));
	}

	/**
	 * Returns the minimum x value on screen
	 *
	 * @return
	 */
	public int getMinX() {
		return (int) (this.x - (XSCREEN / 2));
	}

	/**
	 * Returns the minimum y value on screen
	 *
	 * @return
	 */
	public int getMinY() {
		return (int) (this.y - (YSCREEN / 2));
	}

	/**
	 * @return
	 */
	public int getX() {
		return (int) this.x;
	}

	/**
	 * @return
	 */
	public int getY() {
		return (int) this.y;
	}

	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
