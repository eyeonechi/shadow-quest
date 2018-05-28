/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Abstract Entity Class
 */
public abstract class Entity implements Collision {
	protected final static char LEFT = 'L', RIGHT = 'R';
	protected char orient;
	protected Image image;
	protected String name;
	protected double x, y, xLeft, xRight, yTop, yBottom;

	/**
	 * Creates a new Entity object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @param image
	 *            : image file
	 * @param name
	 *            : name of entity
	 * @throws SlickException
	 */
	public Entity(double x, double y, String image, String name)
			throws SlickException {
		this.image = new Image(image);
		this.name = name;
		this.x = x;
		this.xLeft = this.x - this.image.getWidth() / 2;
		this.xRight = this.x + this.image.getWidth() / 2;
		this.y = y;
		this.yTop = this.y + this.image.getHeight() / 2;
		this.yBottom = this.y - this.image.getHeight() / 2;
	}

	/**
	 * Displays entity's health bar
	 *
	 * @param graphics
	 *            : Used to render objects
	 */
	protected void displayHealth(Graphics graphics) {
	}

	/**
	 * Renders the entity
	 *
	 * @param graphics
	 *            : Used to render objects
	 */
	public void render(Graphics graphics) {
		if (this.orient == LEFT) {
			this.renderLeft();
		} else {
			this.renderRight();
		}
		this.displayHealth(graphics);
	}

	/**
	 * Renders image facing right
	 */
	protected void renderRight() {
		this.image.drawCentered((float) this.x, (float) this.y);
	}

	/**
	 * Renders image facing left
	 */
	protected void renderLeft() {
		this.image.getFlippedCopy(true, false).drawCentered((float) this.x,
				(float) this.y);
	}

	/**
	 * Updates player coordinates to simulate movement
	 *
	 * @param x
	 *            : Distance moved in x-direction
	 * @param y
	 *            : Distance moved in y-direction
	 * @param speed
	 *            : Movement speed multiplier
	 */
	public void update(double x, double y, double speed) {
		this.x += speed * x;
		this.xLeft += speed * x;
		this.xRight += speed * x;
		this.y += speed * y;
		this.yTop += speed * y;
		this.yBottom += speed * y;
	}

	/**
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            : x-coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return
	 */
	public double getxLeft() {
		return xLeft;
	}

	/**
	 * @param xLeft
	 *            : x-coordinate
	 */
	public void setxLeft(double xLeft) {
		this.xLeft = xLeft;
	}

	/**
	 * @return
	 */
	public double getxRight() {
		return xRight;
	}

	/**
	 * @param xRight
	 *            : x-coordinate
	 */
	public void setxRight(double xRight) {
		this.xRight = xRight;
	}

	/**
	 * @return
	 */
	public double getyTop() {
		return yTop;
	}

	/**
	 * @param yTop
	 *            : y-coordinate
	 */
	public void setyTop(double yTop) {
		this.yTop = yTop;
	}

	/**
	 * @return
	 */
	public double getyBottom() {
		return yBottom;
	}

	/**
	 * @param yBottom
	 *            : y-coordinate
	 */
	public void setyBottom(double yBottom) {
		this.yBottom = yBottom;
	}

	/**
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 *            : y-coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return
	 */
	public char getOrient() {
		return orient;
	}

	/**
	 * @param orient
	 *            : direction the entity is facing
	 */
	public void setOrient(char orient) {
		this.orient = orient;
	}

	/**
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 *            : image path
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            : name of entity
	 */
	public void setName(String name) {
		this.name = name;
	}
}
