/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Handles groups of Monster objects
 */
public class Spawn {
	private static final String UNITDATA = "data/units.dat";
	private List<Monster> monsters;

	/**
	 * Creates a new Spawn object
	 *
	 * @param type
	 *            : type of monster
	 * @throws IOException
	 * @throws SlickException
	 */
	public Spawn(String type) throws IOException, SlickException {
		this.monsters = new ArrayList<Monster>();
		this.coordinates(type);
	}

	// Methods
	/**
	 * Reads a file and obtains coordinates
	 *
	 * @param type
	 *            : type of monster
	 * @throws IOException
	 * @throws SlickException
	 */
	public void coordinates(String type) throws IOException, SlickException {
		for (String line : Files.readAllLines(Paths.get(UNITDATA))) {
			try {
				double x = Double.parseDouble(line.split(",")[1]);
				double y = Double.parseDouble(line.split(",")[2]);
				if (line.contains("Bandit") && type == "bandit") {
					this.monsters.add(new Bandit(x, y));
				} else if (line.contains("GiantBat") && type == "dreadbat") {
					this.monsters.add(new Dreadbat(x, y));
				} else if (line.contains("Draelic") && type == "necromancer") {
					this.monsters.add(new Necromancer(x, y));
				} else if (line.contains("Skeleton") && type == "skeleton") {
					this.monsters.add(new Skeleton(x, y));
				} else if (line.contains("Zombie") && type == "zombie") {
					this.monsters.add(new Zombie(x, y));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Renders monster objects
	 *
	 * @param graphics
	 *            : Used for rendering
	 * @throws SlickException
	 */
	public void render(Graphics graphics) throws SlickException {
		for (int i = 0; i < this.monsters.size(); i++) {
			this.monsters.get(i).render(graphics);
		}
	}

	/**
	 * Monster objects wander around
	 *
	 * @param map
	 *            : map file
	 * @param target
	 *            : target entity
	 * @param delta
	 *            : time (in milliseconds) since the last frame update
	 * @param attack
	 *            : toggle for player attack command
	 */
	public void wander(Map map, Player target, int delta, boolean attack) {
		for (int i = 0; i < this.monsters.size(); i++) {
			Monster monster = this.monsters.get(i);
			/** If monster detects a target */
			if (monster.detect(target)) {
				/** If monster collides with a target */
				if (monster.collide(target)) {
					/** If attack key is pressed */
					if (attack) {
						target.attack(monster, delta);
					}
					/** Aggressive monster attack player */
					if (monster.aggressive) {
						monster.attack(target, delta);
					}
					/** Monster dies */
					if (monster.getHealth() <= 0) {
						this.monsters.remove(i);
						continue;
					}
				} else {
					/** Aggressive monster chases target */
					if (monster.aggressive) {
						monster.chase(target, delta);
						/** Passive monster flees from target */
					} else {
						if (monster.getHealth() < monster.getMaxHealth()) {
							monster.flee(target, delta);
						}
					}
				}
			} else {
				/** Monster continues wandering */
				monster.wander(map, delta);
			}
		}
	}

	/**
	 * Returns a list of monsters
	 *
	 * @return
	 */
	public List<Monster> getMonsters() {
		return monsters;
	}

	/**
	 * Sets the list of monsters
	 *
	 * @param monsters
	 *            : list of monsters
	 */
	public void setMonsters(List<Monster> monsters) {
		this.monsters = monsters;
	}
}
