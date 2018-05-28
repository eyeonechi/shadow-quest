/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;

/**
 * Abstract Unit Class Entities with movement
 */
public abstract class Unit extends Entity {
	protected final static int YHEALTHBAR = 18;
	protected int cooldown, maxCooldown;
	protected int damage, maxDamage;
	protected int health, maxHealth;
	protected double speed;

	/**
	 * Creates a new Unit object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @param image
	 *            : image path
	 * @param name
	 *            : name of unit
	 * @throws SlickException
	 */
	public Unit(double x, double y, String image, String name)
			throws SlickException {
		super(x, y, image, name);
	}

	/**
	 * Returns health
	 *
	 * @return
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Sets health
	 *
	 * @param health
	 *            : health value
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Returns max health
	 *
	 * @return
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Sets max health
	 *
	 * @param maxHealth
	 *            : max health value
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * Returns damage
	 *
	 * @return
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Sets damage
	 *
	 * @param damage
	 *            : damage value
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Returns max damage
	 *
	 * @return
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * Sets max damage
	 *
	 * @param maxDamage
	 *            : max damage value
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	/**
	 * Returns cooldown
	 *
	 * @return
	 */
	public int getCooldown() {
		return this.cooldown;
	}

	/**
	 * Sets cooldown
	 *
	 * @param cooldown
	 *            : cooldown value
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	/**
	 * Returns max cooldown
	 *
	 * @return
	 */
	public int getMaxCooldown() {
		return this.maxCooldown;
	}

	/**
	 * Sets max cooldown
	 *
	 * @param maxCooldown
	 *            : max cooldown value
	 */
	public void setMaxCooldown(int maxCooldown) {
		this.maxCooldown = maxCooldown;
	}

	/**
	 * Returns speed
	 *
	 * @return
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets speed
	 *
	 * @param speed
	 *            : speed value
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
