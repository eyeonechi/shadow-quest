/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Bandit class
 */
public class Bandit extends Monster {
	private final static String IMAGE = "assets/units/bandit.png";
	private final static String NAME = "Bandit";
	private final static int COOLDOWN = 200;
	private final static int DAMAGE = 8;
	private final static int HEALTH = 40;
	private final static boolean AGGRESSIVE = true;

	/**
	 * Creates a new Bandit object
	 *
	 * @param x
	 *            : x-position
	 * @param y
	 *            : y-position
	 * @throws SlickException
	 */
	public Bandit(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
		this.aggressive = AGGRESSIVE;
	}
}
