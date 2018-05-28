/* SWEN20003 Object Oriented Software Development
 * Project 2 - RPG Game Engine
 * Author: <Ivan Ken Weng Chee> <ichee>
 */

package swen20003;

import java.io.IOException;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.Path;

/**
 * Represents the entire game world (Designed to be instantiated just once for
 * the whole game)
 */
public class World {
	private final static int XPLAYER = 756, YPLAYER = 684;
	private final static int XALDRIC = 550, YALDRIC = 600;
	private final static int XELVIRA = 900, YELVIRA = 525;
	private final static int XGARTH = 825, YGARTH = 825;
	private final static int XAMULET = 965, YAMULET = 3563;
	private final static int XELIXIR = 1976, YELIXIR = 402;
	private final static int XSWORD = 546, YSWORD = 6707;
	private final static int XTOME = 4791, YTOME = 1253;

	private Map map;
	private Player player;
	private Villager aldric, elvira, garth;
	private Item amulet, tome, elixir, sword;
	private Camera camera;
	private Panel panel;
	private PathFinder pathFinder;
	private Spawn bandits, dreadbats, necromancers, skeletons, zombies;
	private Path path;
	private boolean traverse = false;

	/**
	 * Create a new World object.
	 *
	 * @param xScreen
	 *            : Width of game window
	 * @param yScreen
	 *            : Height of game window
	 * @throws IOException
	 */
	public World(int xScreen, int yScreen) throws SlickException, IOException {
		this.map = new Map();
		this.pathFinder = new PathFinder(this.map);
		this.player = new Player(XPLAYER, YPLAYER);
		this.aldric = new Prince(XALDRIC, YALDRIC);
		this.elvira = new Shaman(XELVIRA, YELVIRA);
		this.garth = new Peasant(XGARTH, YGARTH);
		this.camera = new Camera(xScreen, yScreen, this.map.getMapX(),
				this.map.getMapY());
		this.panel = new Panel(this.player, xScreen, yScreen,
				this.map.getMapX(), this.map.getMapY());
		this.bandits = new Spawn("bandit");
		this.dreadbats = new Spawn("dreadbat");
		this.necromancers = new Spawn("necromancer");
		this.skeletons = new Spawn("skeleton");
		this.zombies = new Spawn("zombie");
		this.amulet = new Amulet(XAMULET, YAMULET);
		this.elixir = new Elixir(XELIXIR, YELIXIR);
		this.sword = new Sword(XSWORD, YSWORD);
		this.tome = new Tome(XTOME, YTOME);
	}

	/**
	 * Handles object calculations during each update loop
	 *
	 * @param xDir
	 *            : The player's movement in the x axis (-1, 0 or 1)
	 * @param yDir
	 *            : The player's movement in the y axis (-1, 0 or 1)
	 * @param delta
	 *            : Time passed since last frame (milliseconds)
	 * @param xDest
	 *            : Destination x-coordinate (- 1 if not used)
	 * @param yDest
	 *            : Destination y-coordinate (- 1 if not used)
	 * @throws SlickException
	 */
	public void update(double xDir, double yDir, int delta, boolean attack)
			throws SlickException {
		this.player.walk(this.map, xDir * delta, yDir * delta);
		this.camera.follow(this.player);
		this.panel.follow(this.player);
		this.bandits.wander(this.map, this.player, delta, attack);
		this.dreadbats.wander(this.map, this.player, delta, attack);
		this.necromancers.wander(this.map, this.player, delta, attack);
		this.skeletons.wander(this.map, this.player, delta, attack);
		this.zombies.wander(this.map, this.player, delta, attack);
		if (this.player.getHealth() <= 0) {
			player.respawn(XPLAYER, YPLAYER);
		}
		if (player.collide(this.amulet)) {
			this.player.pickItem(this.amulet);
			this.player.useItem(this.amulet);
		}
		if (player.collide(this.tome)) {
			this.player.pickItem(this.tome);
			this.player.useItem(this.tome);
		}
		if (player.collide(this.elixir)) {
			this.player.pickItem(this.elixir);
			this.player.useItem(this.elixir);
		}
		if (player.collide(this.sword)) {
			this.player.pickItem(this.sword);
			this.player.useItem(this.sword);
		}
		if (this.elvira.collide(this.player)) {
			this.elvira.heal(this.player);
		}
	}

	/**
	 * Render the entire screen, so it reflects the current game state
	 *
	 * @param g
	 *            : The Slick graphics object, used for drawing
	 * @throws SlickException
	 */
	public void render(Graphics graphics, boolean talk) throws SlickException {
		this.map.render(this.camera.getX(), this.camera.getY());
		this.translateGraphics(graphics, this.camera.getX(), this.camera.getY());
		this.aldric.notice(graphics, this.player, talk);
		this.elvira.notice(graphics, this.player, talk);
		this.garth.notice(graphics, this.player, talk);
		this.bandits.render(graphics);
		this.dreadbats.render(graphics);
		this.necromancers.render(graphics);
		this.skeletons.render(graphics);
		this.zombies.render(graphics);
		this.aldric.render(graphics);
		this.elvira.render(graphics);
		this.garth.render(graphics);
		this.amulet.render(graphics);
		this.elixir.render(graphics);
		this.sword.render(graphics);
		this.tome.render(graphics);
		this.player.render(graphics);
		this.panel.render(graphics);
	}

	/**
	 * Translates the graphics to follow the map
	 *
	 * @param g
	 *            : The Slick graphics object, used for drawing
	 * @param x
	 *            : Translate horizontal offset
	 * @param y
	 *            : Translate vertical offset
	 */
	private void translateGraphics(Graphics g, double x, double y) {
		g.translate(-(float) x, -(float) y);
	}

	/**
	 * Click-to-move controls
	 *
	 * @param xMouse
	 *            : x-destination
	 * @param yMouse
	 *            : y-destination
	 * @param delta
	 *            : Time passed since last frame (milliseconds)
	 * @throws SlickException
	 */
	public boolean traverse(double xMouse, double yMouse, int delta)
			throws SlickException {
		this.camera.follow(this.player);
		this.panel.follow(this.player);
		if (!traverse) {
			int xDest = (int) xMouse + this.camera.getX();
			int yDest = (int) yMouse + this.camera.getY();
			if (this.pathFinder.search(this.player, xDest, yDest)) {
				traverse = true;
				return true;
			} else {
				System.out.println("Invalid location");
				return false;
			}
		} else {
			this.path = this.pathFinder.getPath();

			if (!this.player.traverse(this.map, this.path, delta)) {
				traverse = false;
				return false;
			}
			return true;
		}
	}
}
