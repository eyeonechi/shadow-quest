/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Zombie Class
 */
public class Zombie extends Monster {
	private final static String IMAGE = "assets/units/zombie.png";
	private final static String NAME = "Zombie";
	private final static int COOLDOWN = 800;
	private final static int DAMAGE = 10;
	private final static int HEALTH = 60;
	private final static boolean AGGRESSIVE = true;

	/**
	 * Creates a new Zombie object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Zombie(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
		this.aggressive = AGGRESSIVE;
	}
}
