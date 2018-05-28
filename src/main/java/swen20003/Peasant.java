/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Peasant Class
 */
public class Peasant extends Villager {
	private final static String IMAGE = "assets/units/peasant.png";
	private final static String NAME = "Garth";
	private final static int COOLDOWN = 0;
	private final static int DAMAGE = 0;
	private final static int HEALTH = 1;

	/**
	 * Creates a new Peasant object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Peasant(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
	}
}
