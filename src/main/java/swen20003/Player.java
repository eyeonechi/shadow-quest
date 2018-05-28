/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;

/**
 * Represents the main character of the game, and the protagonist of this story
 */
public class Player extends Unit implements Movement, Mover, Collision, Combat,
		Interaction {
	private final static String IMAGE = "assets/units/player.png";
	private final static String NAME = "Player";
	private final static double EPSILON = 10.0;
	private final static double SPEED = 0.25;
	private final static int HEALTH = 100;
	private final static int DAMAGE = 26;
	private final static int COOLDOWN = 600;
	private Item[] inventory;
	private static int i = 1;

	/**
	 * Initialises player with given coordinates
	 *
	 * @param x
	 *            : Initial x-coordinate of player
	 * @param y
	 *            : Initial y-coordinate of player
	 */
	public Player(double x, double y) throws SlickException {
		super(x, y, IMAGE, NAME);
		this.cooldown = this.maxCooldown = COOLDOWN;
		this.damage = this.maxDamage = DAMAGE;
		this.health = this.maxHealth = HEALTH;
		this.speed = SPEED;
		this.inventory = new Item[4];
	}

	// Methods
	/**
	 * @param x
	 *            : respawn x-coordinate
	 * @param y
	 *            : respawn y-coordinate
	 */
	public void respawn(double x, double y) {
		this.setHealth(this.getMaxHealth());
		this.x = x;
		this.xLeft = this.x - this.image.getWidth() / 2;
		this.xRight = this.x + this.image.getWidth() / 2;
		this.y = y;
		this.yTop = this.y + this.image.getHeight() / 2;
		this.yBottom = this.y - this.image.getHeight() / 2;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Collision#collide(src.Entity)
	 */
	@Override
	public boolean collide(Entity entity) {
		if ((Math.pow(entity.getX() - this.x, 2.0) + Math.pow(entity.getY()
				- this.y, 2.0)) < Math.pow(COLLIDERANGE, 2.0)) {
			return true;
		}
		return false;
	}

	// Combat Interface Methods
	/*
	 * (non-Javadoc)
	 *
	 * @see src.Combat#attack(src.Unit, int)
	 */
	@Override
	public void attack(Unit unit, int delta) {
		this.setCooldown(this.getCooldown() - delta);
		if (this.getCooldown() < 0) {
			if (Math.floorMod(this.getCooldown(), delta) == 0) {
				unit.setHealth(unit.getHealth()
						- (int) (Math.random() * this.getDamage()));
			}
			this.setCooldown(this.getMaxCooldown());
		}
	}

	// Movement Interface Methods
	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#traverse(src.Map,
	 * org.newdawn.slick.util.pathfinding.Path, int)
	 */
	@Override
	public boolean traverse(Map map, Path path, int delta) {
		if (i < path.getLength()) {
			double xSrc = this.x, ySrc = this.y;
			double xDest = path.getX(i) * map.getTileX() + map.getTileX() / 2;
			double yDest = path.getY(i) * map.getTileY() + map.getTileY() / 2;
			if (!((Math.abs(xSrc - xDest) < EPSILON) && (Math.abs(ySrc - yDest) < EPSILON))) {
				double x = 0, y = 0;
				if (!(Math.abs(xSrc - xDest) < EPSILON)) {
					if (xSrc < xDest) {
						x += 1.0;
					} else {
						x -= 1.0;
					}
					this.update(x * delta, y, SPEED);
				}
				if (!(Math.abs(ySrc - yDest) < EPSILON)) {
					if (ySrc < yDest) {
						y += 1.0;
					} else {
						y -= 1.0;
					}
					this.update(0, y * delta, SPEED);
				}
			} else {
				i++;
			}
			return true;
		} else {
			i = 1;
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#walk(src.Map, double, double)
	 */
	@Override
	public void walk(Map map, double x, double y) {
		if ((this.getX() + x) < this.getX()) {
			this.walkLeft(map, x);
		} else if ((this.getX() + x) > this.getX()) {
			this.walkRight(map, x);
		}
		if ((this.getY() + y) < this.getY()) {
			this.walkDown(map, y);
		} else if ((this.getY() + y) > this.getY()) {
			this.walkUp(map, y);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#walkLeft(src.Map, double)
	 */
	@Override
	public void walkLeft(Map map, double x) {
		this.setOrient(LEFT);
		int leftTileIndex = map.getTileIndexX(this.xLeft + x / 2);
		int yTileIndex = map.getTileIndexY(this.getY());
		if (map.getTilePropertyX(leftTileIndex, yTileIndex)) {
			this.update(x, 0, SPEED);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#walkRight(src.Map, double)
	 */
	@Override
	public void walkRight(Map map, double x) {
		this.setOrient(RIGHT);
		int rightTileIndex = map.getTileIndexX(this.xRight + x / 2);
		int yTileIndex = map.getTileIndexY(this.getY());
		if (map.getTilePropertyX(rightTileIndex, yTileIndex)) {
			this.update(x, 0, SPEED);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#walkUp(src.Map, double)
	 */
	@Override
	public void walkUp(Map map, double y) {
		int upTileIndex = map.getTileIndexY(this.yTop + y / 2);
		int xTileIndex = map.getTileIndexX(this.getX());
		if (map.getTilePropertyY(xTileIndex, upTileIndex)) {
			this.update(0, y, SPEED);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#walkDown(src.Map, double)
	 */
	@Override
	public void walkDown(Map map, double y) {
		int downTileIndex = map.getTileIndexY(this.yBottom + y / 2);
		int xTileIndex = map.getTileIndexX(this.getX());
		if (map.getTilePropertyY(xTileIndex, downTileIndex)) {
			this.update(0, y, SPEED);
		}
	}

	// Support Interface Methods
	/*
	 * (non-Javadoc)
	 *
	 * @see src.Interaction#pickItem(src.Item)
	 */
	@Override
	public void pickItem(Item item) {
		if (item.getName() == "Amulet") {
			this.inventory[AMULETPOS] = item;
		} else if (item.getName() == "Tome") {
			this.inventory[TOMEPOS] = item;
		} else if (item.getName() == "Elixir") {
			this.inventory[ELIXIRPOS] = item;
		} else if (item.getName() == "Sword") {
			this.inventory[SWORDPOS] = item;
		}
		item.remove();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Interaction#useItem(src.Item)
	 */
	@Override
	public void useItem(Item item) {
		item.enhance(this);
	}

	// Get Setters
	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#getX()
	 */
	@Override
	public double getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#getY()
	 */
	@Override
	public double getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#setX(double)
	 */
	@Override
	public void setX(double x) {
		this.x = x;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#setY(double)
	 */
	@Override
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Returns player's inventory
	 *
	 * @return
	 */
	public Item[] getInventory() {
		return inventory;
	}

	/**
	 * Sets player's inventory
	 *
	 * @param inventory
	 */
	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}

}
