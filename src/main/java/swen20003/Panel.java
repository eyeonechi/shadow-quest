/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Panel Class
 */
public class Panel {
	private static final String PANEL = "assets/panel.png";
	public static final int PANELHEIGHT = 70;
	private final int XSCREEN, YSCREEN, XMAP, YMAP;
	private double x, y;
	private Image image;
	private Player player;

	/**
	 * Creates a new panel object
	 *
	 * @param player
	 *            : player to follow
	 * @param xScreen
	 *            : width of screen
	 * @param yScreen
	 *            : height of screen
	 * @param xMap
	 *            : width of map
	 * @param yMap
	 *            : height of map
	 * @throws SlickException
	 */
	public Panel(Player player, int xScreen, int yScreen, int xMap, int yMap)
			throws SlickException {
		this.player = player;
		this.image = new Image(PANEL);
		XSCREEN = xScreen;
		YSCREEN = yScreen;
		XMAP = xMap;
		YMAP = yMap;
	}

	/**
	 * Renders a panel
	 *
	 * @param graphics
	 *            : Used for rendering
	 */
	public void render(Graphics graphics) {
		this.image.draw((float) this.x, (float) this.y + PANELHEIGHT);
		this.displayStatus(graphics);
	}

	/**
	 * Follows a player
	 *
	 * @param player
	 *            : player to follow
	 */
	public void follow(Player player) {
		this.player = player;
		this.update();
	}

	/**
	 * Display status of followed entity
	 *
	 * @param graphics
	 *            : Used for rendering
	 */
	private void displayStatus(Graphics graphics) {
		// Panel colours
		Color LABEL = new Color(0.9f, 0.9f, 0.4f); // Gold
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black, transp
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red, transp

		// Variables for layout
		String text; // Text to display
		int text_x, text_y; // Coordinates to draw text
		int bar_x, bar_y; // Coordinates to draw rectangles
		int bar_width, bar_height; // Size of rectangle to draw
		int hp_bar_width; // Size of red (HP) rectangle
		int inv_x, inv_y; // Coordinates to draw inventory item

		float health_percent; // Player's health, as a percentage

		// Panel background image
		// Panel.draw(0, YSCREEN - PANELHEIGHT);

		// Display the player's health
		text_x = (int) this.x + 15;
		text_y = (int) this.y + PANELHEIGHT + 25;
		graphics.setColor(LABEL);
		graphics.drawString("Health:", text_x, text_y);
		text = Integer.toString(this.player.getHealth()) + "/"
				+ Integer.toString(this.player.getMaxHealth());

		bar_x = (int) this.x + 90;
		bar_y = (int) this.y + PANELHEIGHT + 20;
		bar_width = 90;
		bar_height = 30;
		health_percent = (float) this.player.getHealth()
				/ this.player.getMaxHealth();
		hp_bar_width = (int) (bar_width * health_percent);
		text_x = bar_x + (bar_width - graphics.getFont().getWidth(text)) / 2;
		graphics.setColor(BAR_BG);
		graphics.fillRect(bar_x, bar_y, bar_width, bar_height);
		graphics.setColor(BAR);
		graphics.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
		graphics.setColor(VALUE);
		graphics.drawString(text, text_x, text_y);

		// Display the player's damage and cooldown
		text_x = (int) this.x + 200;
		graphics.setColor(LABEL);
		graphics.drawString("Damage:", text_x, text_y);
		text_x += 80;
		text = Integer.toString(this.player.getMaxDamage());
		graphics.setColor(VALUE);
		graphics.drawString(text, text_x, text_y);
		text_x += 40;
		graphics.setColor(LABEL);
		graphics.drawString("Rate:", text_x, text_y);
		text_x += 55;
		text = Integer.toString(this.player.getMaxCooldown());
		graphics.setColor(VALUE);
		graphics.drawString(text, text_x, text_y);

		// Display the player's inventory
		graphics.setColor(LABEL);
		graphics.drawString("Items:", (int) this.x + 420, text_y);
		bar_x = (int) this.x + 490;
		bar_y = (int) this.y + PANELHEIGHT + 10;
		bar_width = 288;
		bar_height = bar_height + 20;
		graphics.setColor(BAR_BG);
		graphics.fillRect(bar_x, bar_y, bar_width, bar_height);

		inv_x = (int) this.x + 490;
		inv_y = (int) this.y + PANELHEIGHT + ((PANELHEIGHT - 72) / 2);

		for (int i = 0; i < this.player.getInventory().length; i++) {
			// Render the item to (inv_x, inv_y)
			if (this.player.getInventory()[i] != null) {
				this.player.getInventory()[i].render(inv_x, inv_y);
			}
			inv_x += 72;
		}
	}

	/**
	 * Updates panel coordinates
	 */
	private void update() {
		if ((this.player.getX() - XSCREEN / 2) < 0) {
			this.x = 0;
		} else if ((this.player.getX() + XSCREEN / 2) > XMAP) {
			this.x = XMAP - XSCREEN;
		} else {
			this.x = this.player.getX() - XSCREEN / 2;
		}
		if ((this.player.getY() - YSCREEN / 2) < 0) {
			this.y = YSCREEN - this.image.getHeight();
		} else if ((this.player.getY() + YSCREEN / 2) > YMAP) {
			this.y = YMAP - this.image.getHeight();
		} else {
			this.y = this.player.getY() + YSCREEN / 2 - this.image.getHeight();
		}
	}
}
