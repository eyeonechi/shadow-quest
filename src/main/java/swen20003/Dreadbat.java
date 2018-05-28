/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Dreadbat Class
 */
public class Dreadbat extends Monster {
	private final static String IMAGE = "assets/units/dreadbat.png";
	private final static String NAME = "GiantBat";
	private final static int COOLDOWN = 0;
	private final static int DAMAGE = 0;
	private final static int HEALTH = 40;
	private final static boolean AGGRESSIVE = false;

	/**
	 * Creates a new Giant Bat object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Dreadbat(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
		this.aggressive = AGGRESSIVE;
	}
}
