/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Skeleton Class
 */
public class Skeleton extends Monster {
	private final static String IMAGE = "assets/units/skeleton.png";
	private final static String NAME = "Skeleton";
	private final static int COOLDOWN = 500;
	private final static int DAMAGE = 16;
	private final static int HEALTH = 100;
	private final static boolean AGGRESSIVE = true;

	/**
	 * Creates a new skeleton object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Skeleton(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
		this.aggressive = AGGRESSIVE;
	}
}
