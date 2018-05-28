/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Abstract Villager Class
 */
public abstract class Villager extends Unit implements Collision, Interaction,
		Support, Speech {
	/**
	 * Creates a new villager object
	 *
	 * @param x
	 *            : x-coordinate
	 * @param y
	 *            : y-coordinate
	 * @param image
	 *            : image path
	 * @param name
	 *            : name of villager
	 * @throws SlickException
	 */
	public Villager(double x, double y, String image, String name)
			throws SlickException {
		super(x, y, image, name);
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

	// Collision Interface Methods
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
	 * @see src.Collision#notice(org.newdawn.slick.Graphics, src.Player,
	 * boolean)
	 */
	@Override
	public void notice(Graphics graphics, Player player, boolean talk) {
		if (player.getX() < this.x) {
			this.orient = LEFT;
		} else {
			this.orient = RIGHT;
		}
		if (this.collide(player)) {
			if (talk) {
				this.speak(graphics, player);
			}
		}
	}

	// Speech Interface Methods
	/*
	 * (non-Javadoc)
	 *
	 * @see src.Speech#speak(org.newdawn.slick.Graphics, src.Player)
	 */
	@Override
	public void speak(Graphics graphics, Player player) {
		Color backgroundColor = new Color(0.0f, 0.0f, 0.0f, 0.8f);
		Color textColor = new Color(1.0f, 1.0f, 1.0f);
		String text;
		if (this.getName() == "Aldric") {
			if (player.getInventory()[Interaction.ELIXIRPOS] == null) {
				text = "Please seek out the Elixir of Life to cure the king.";
			} else {
				text = "The elixir! My father is cured! Thankyou!";
			}
		} else if (this.getName() == "Elvira") {
			if (player.getHealth() == player.getMaxHealth()) {
				text = "Return to me if you ever need healing.";
			} else {
				text = "You're looking much healthier now.";
			}
		} else {
			if (player.getInventory()[Interaction.AMULETPOS] == null) {
				text = "Find the Amulet of Vitality, across the river to the west.";
			} else if (player.getInventory()[Interaction.SWORDPOS] == null) {
				text = "Find the Sword of Strength - cross the river and back, on the east side.";
			} else if (player.getInventory()[Interaction.TOMEPOS] == null) {
				text = "Find the Tome of Agility, in the Land of Shadows.";
			} else {
				text = "You have found all the treasure I know of.";
			}
		}
		int text_x, text_y;
		int bar_x, bar_y;
		int bar_width, bar_height;
		text_x = (int) (this.getX() - this.getImage().getWidth() / 2);
		text_y = (int) (this.getY() - this.getImage().getHeight());
		bar_x = text_x;
		bar_y = text_y;
		bar_width = text.length() * 10;
		bar_height = 22;
		graphics.setColor(backgroundColor);
		graphics.fillRect(bar_x - bar_width / 2, bar_y, bar_width, bar_height);
		graphics.setColor(textColor);
		graphics.drawString(text, text_x - bar_width / 2, text_y);
	}
}
