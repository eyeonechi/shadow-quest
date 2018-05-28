/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

/**
 * Support Interface
 */
public interface Support {
	public static final int AMULETVITALITY = 80, SWORDDAMAGE = 30,
			TOMEAGILITY = 300;

	/**
	 * Unit heals another unit
	 *
	 * @param unit
	 *            : unit to be healed
	 */
	public default void heal(Unit unit) {
		unit.setHealth(unit.getMaxHealth());
	}

	/**
	 * Enhances damage stat of a unit
	 *
	 * @param unit
	 *            : unit to be enhanced
	 */
	public default void enhanceDamage(Unit unit) {
		unit.setMaxDamage(unit.getMaxDamage() + SWORDDAMAGE);
	}

	/**
	 * Enhances damage stat of a unit
	 *
	 * @param unit
	 *            : unit to be enhanced
	 */
	public default void enhanceVitality(Unit unit) {
		unit.setMaxHealth(unit.getMaxHealth() + AMULETVITALITY);
	}

	/**
	 * Enhances damage stat of a unit
	 *
	 * @param unit
	 *            : unit to be enhanced
	 */
	public default void enhanceAgility(Unit unit) {
		unit.setMaxCooldown(unit.getMaxCooldown() - TOMEAGILITY);
	}
}
