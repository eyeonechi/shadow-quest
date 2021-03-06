/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Prince Class
 */
public class Prince extends Villager {
	private final static String IMAGE = "assets/units/prince.png";
	private final static String NAME = "PrinceAldric";
	private final static int COOLDOWN = 0;
	private final static int DAMAGE = 0;
	private final static int HEALTH = 1;

	/**
	 * Creates a new Prince object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Prince(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
	}
}
