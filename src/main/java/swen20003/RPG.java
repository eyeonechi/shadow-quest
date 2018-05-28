/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: Matt Giuca <mgiuca>
 * Modifications: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Main class for the Role-Playing Game engine * Handles initialisation, input
 * and rendering
 */
public class RPG extends BasicGame {
	private World world;
	double xMouse, yMouse;
	boolean traverse = false;
	boolean attack = false;
	boolean talk = false;
	public static final int YSCREEN = 600;
	public static final int XSCREEN = 800;
	private Font font;

	/**
	 * Create a new RPG object
	 *
	 */
	public RPG() {
		super("RPG Game Engine");
	}

	/**
	 * Initialise the game state *
	 *
	 * @param gc
	 *            : The Slick game container object
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		try {
			this.world = new World(XSCREEN, YSCREEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.font = FontLoader.loadFont("assets/DejaVuSans-Bold.ttf", 15);
	}

	/**
	 * Update the game state for a frame *
	 *
	 * @param gc
	 *            : The Slick game container object *
	 * @param delta
	 *            : Time passed since last frame (milliseconds)
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		/** Get data about the current input (keyboard state) */
		Input input = gc.getInput();
		this.attack = false;

		if (traverse) {
			if (!this.world.traverse(xMouse, yMouse, delta)) {
				traverse = false;
			}
			if (input.isKeyDown(Input.KEY_DOWN)
					|| input.isKeyDown(Input.KEY_UP)
					|| input.isKeyDown(Input.KEY_LEFT)
					|| input.isKeyDown(Input.KEY_RIGHT)) {
				traverse = false;
			}
		} else {
			/** Exits the game if the Escape key is pressed */
			if (input.isKeyPressed(Input.KEY_ESCAPE)) {
				System.exit(0);
			}

			/** Update the player's movement direction based on keyboard presses */
			double dir_x = 0, dir_y = 0;
			if (input.isKeyDown(Input.KEY_DOWN)) {
				dir_y += 1;
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				dir_y -= 1;
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				dir_x -= 1;
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				dir_x += 1;
			}

			/** Attack command */
			if (input.isKeyDown(Input.KEY_A)) {
				this.attack = true;
			}
			/** Talk toggle */
			if (input.isKeyPressed(Input.KEY_T)) {
				if (this.talk) {
					this.talk = false;
				} else {
					this.talk = true;
				}
			}

			/** Updates the player's movement direction based on mouse clicks */
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				traverse = true;
				xMouse = input.getMouseX();
				yMouse = input.getMouseY();
				this.world.traverse(xMouse, yMouse, delta);
			} else {
				xMouse = -1;
				yMouse = -1;
				/** Let World.update decide what to do with this data */
				this.world.update(dir_x, dir_y, delta, attack);
			}
		}
	}

	/**
	 * Render the entire screen, so it reflects the current game state *
	 *
	 * @param gc
	 *            : The Slick game container object *
	 * @param g
	 *            : The Slick graphics object, used for drawing
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setFont(this.font);
		/** Let World.render handle the rendering */
		this.world.render(g, this.talk);
	}

	/**
	 * Start-up method. Creates the game and runs it *
	 *
	 * @param args
	 *            : Command-line arguments (ignored)
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new RPG());
		app.setVSync(true);
		app.setShowFPS(true);
		app.setDisplayMode(XSCREEN, YSCREEN, false);
		app.start();
	}
}
