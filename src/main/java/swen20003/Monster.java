/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Monster Class
 */
public abstract class Monster extends Unit implements Combat, Collision,
		Movement {
	private final static double SPEED = 0.2;
	protected boolean aggressive;

	/**
	 * Creates a new Monster object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @param image
	 *            : image path
	 * @param name
	 *            : monster name
	 * @throws SlickException
	 */
	public Monster(double x, double y, String image, String name)
			throws SlickException {
		super(x, y, image, name);
		this.speed = SPEED;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#displayHealth(org.newdawn.slick.Graphics)
	 */
	@Override
	protected void displayHealth(Graphics graphics) {
		float healthPercent = (float) this.getHealth()
				/ (float) this.getMaxHealth() * this.image.getWidth();
		graphics.fillRect((float) (this.x - this.image.getWidth() / 2),
				(float) (this.y - this.image.getHeight() / 1.5),
				this.image.getWidth(), YHEALTHBAR);
		graphics.setColor(Color.red);
		graphics.fillRect((float) (this.x - this.image.getWidth() / 2),
				(float) (this.y - this.image.getHeight() / 1.5), healthPercent,
				YHEALTHBAR);
		graphics.setColor(Color.white);
		graphics.drawString(name, (float) (this.x - this.image.getWidth() / 2),
				(float) (this.y - this.image.getHeight() / 1.5));
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

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Collision#detect(src.Entity)
	 */
	@Override
	public boolean detect(Entity entity) {
		if ((Math.pow(entity.getX() - this.x, 2.0) + Math.pow(entity.getY()
				- this.y, 2.0)) < Math.pow(DETECTRANGE, 2.0)) {
			return true;
		}
		return false;
	}

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
	 * @see src.Movement#chase(src.Entity, int)
	 */
	@Override
	public void chase(Entity entity, int delta) {
		double xDist = Math.abs(entity.getX() - this.x), yDist = Math
				.abs(entity.getY() - this.y);
		double amount = delta;
		double totalDist = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
		double dx = xDist / totalDist * amount, dy = yDist / totalDist * amount;
		if (this.x < entity.getX()) {
			this.update(dx, 0, SPEED);
		} else {
			this.update(-dx, 0, SPEED);
		}
		if (this.y < entity.getY()) {
			this.update(0, dy, SPEED);
		} else {
			this.update(0, -dy, SPEED);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#flee(src.Entity, int)
	 */
	@Override
	public void flee(Entity entity, int delta) {
		double xDist = Math.abs(entity.getX() - this.x), yDist = Math
				.abs(entity.getY() - this.y);
		double amount = delta;
		double totalDist = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
		double dx = xDist / totalDist * amount, dy = yDist / totalDist * amount;
		if (this.x < entity.getX()) {
			this.update(-dx, 0, SPEED);
		} else {
			this.update(dx, 0, SPEED);
		}
		if (this.y < entity.getY()) {
			this.update(0, -dy, SPEED);
		} else {
			this.update(0, dy, SPEED);
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

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Movement#wander(src.Map, int)
	 */
	@Override
	public void wander(Map map, int delta) {
		int direction = (int) (Math.random() * 9);
		if (direction == 1) {
			this.walk(map, 0, delta);
		} else if (direction == 2) {
			this.walk(map, delta, delta);
		} else if (direction == 3) {
			this.walk(map, delta, 0);
		} else if (direction == 4) {
			this.walk(map, delta, -delta);
		} else if (direction == 5) {
			this.walk(map, 0, -delta);
		} else if (direction == 6) {
			this.walk(map, -delta, -delta);
		} else if (direction == 7) {
			this.walk(map, -delta, 0);
		} else if (direction == 8) {
			this.walk(map, -delta, delta);
		} else {
			this.walk(map, 0, 0);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Entity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#getHealth()
	 */
	@Override
	public int getHealth() {
		return this.health;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#setHealth(int)
	 */
	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#getMaxHealth()
	 */
	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#setMaxHealth(int)
	 */
	@Override
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#getDamage()
	 */
	@Override
	public int getDamage() {
		return this.damage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#setDamage(int)
	 */
	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#getMaxDamage()
	 */
	@Override
	public int getMaxDamage() {
		return maxDamage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#setMaxDamage(int)
	 */
	@Override
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#getCooldown()
	 */
	@Override
	public int getCooldown() {
		return this.cooldown;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#setCooldown(int)
	 */
	@Override
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#getMaxCooldown()
	 */
	@Override
	public int getMaxCooldown() {
		return this.maxCooldown;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Unit#setMaxCooldown(int)
	 */
	@Override
	public void setMaxCooldown(int maxCooldown) {
		this.maxCooldown = maxCooldown;
	}
}
