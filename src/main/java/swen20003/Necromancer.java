/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Necromancer Class
 */
public class Necromancer extends Monster {
	private final static String IMAGE = "assets/units/necromancer.png";
	private final static String NAME = "Draelic";
	private final static int COOLDOWN = 400;
	private final static int DAMAGE = 30;
	private final static int HEALTH = 140;
	private final static boolean AGGRESSIVE = true;

	/**
	 * Creates a new Necromancer object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @throws SlickException
	 */
	public Necromancer(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
		this.aggressive = AGGRESSIVE;
	}
}
