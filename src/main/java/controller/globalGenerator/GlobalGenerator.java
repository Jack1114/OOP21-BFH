package controller.globalGenerator;
import java.util.*;

import controller.abilities.AbilityManager;
import controller.enemies.Enemy_move_control;
import controller.obstacles.*;
import controller.playerAttack.PlayerAttack;
import controller.playerAttack.PlayerAttackImpl;
import controller.playerMovements.PlayerMovement;
import controller.playerMovements.PlayerMovementImpl;
import model.obstacles.*;
import model.player.*;
import model.abilities.*;
import model.enemies.Enemy;
import model.obstacles.Obstacle;
import view.GameLayoutController;

/**
 * Generates all the entities of the game: - Arena - Obstacles - Abilities -
 * Player - Enemies Manages the rounds and the turns.
 *
 */
public class GlobalGenerator {

	private static final int GRID_SIZE_X = 10;
	private static final int GRID_SIZE_Y = 12;
	private static final int MAX_ROUNDS = 50;
	private static final int NUM_ENEMIES = 3;

	public List<Pair<Integer, Pair<Integer, Integer>>> enemyposwithID = new ArrayList<>();
	public List<Obstacle> obstacles = new ArrayList<>();
	public Map<Ability.Type, Integer> abilities = new HashMap<>();
	public AbilityManager abilityManager;
	private ObstacleGenerator obstacleGenerator;
	public Player player;
	public PlayerAttack playerAttack;
	public PlayerMovement playerMovement;
	public List<Enemy> enemies;
	public List<Integer> skipenemy;
	private int round;

	GameLayoutController g;

	private static GlobalGenerator instance = null;

	private GlobalGenerator(GameLayoutController gameL) {
		this.g = gameL;
	}

	/**
	 * @return instance
	 */
	public static synchronized GlobalGenerator getInstance() {
		return instance;
	}

	/**
	 * @param gameL
	 */
	public static synchronized void setInstance(GameLayoutController gameL) {
		instance = new GlobalGenerator(gameL);
	}

	/**
	 * @return GRID_SIZE_X
	 * la dimensione x of the play area 
	 */
	public static int getGridSizeX() {
		return GRID_SIZE_X;
	}

	/**
	 * @return GRID_SIZE_Y
	 * la dimensione y of the play area
	 */
	public static int getGridSizeY() {
		return GRID_SIZE_Y;
	}

	/**
	 * 
	 */
	public void play() {

		System.out.println("---- Round " + round + " ----");
		System.out.println("Killed enemies: " + skipenemy.size());

		if (skipenemy.size() == NUM_ENEMIES) {
			System.out.println("Congrats, you killed all the enemies! New arena generated.");
			reset();
		}
		playerTurn();
		round++;
	}

	/*
	 * First generation of Entities.
	 */
	/**
	 * generate the pay area whit all the components :player, obstacles, enemies
	 */
	public void generation() {
		this.obstacleGenerator = new ObstacleGenerator(obstacles);
		this.player = new PlayerImpl(rand_pos_player(GRID_SIZE_X, GRID_SIZE_Y));
		this.playerAttack = new PlayerAttackImpl(player);
		this.playerMovement = new PlayerMovementImpl(player);
		this.enemies = new ArrayList<Enemy>();
		this.skipenemy = new ArrayList<>();

		abilities.put(Ability.Type.ELIXIR_OF_LIFE, 2);
		abilities.put(Ability.Type.DOUBLE_ATTACK, 3);
		this.abilityManager = new AbilityManager(abilities);
		abilityManager.generateAbilities();

		generateArena();

		round = 0;
	}

	/**
	 * Generate the Arena.
	 */
	private void generateArena() {
		obstacleGenerator.generateObstacles();
		generate_enemies();
		g.update();
	}

	/**
	 * Once all the enemies are killed, this method clear all the current obstacles
	 * and generate new Enemies and a new Arena, resetting the Round counter as
	 * well.
	 */
	private void reset() {
		obstacles.removeAll(obstacles);
		enemyposwithID = new ArrayList<>();
		enemies = new ArrayList<Enemy>();
		skipenemy = new ArrayList<Integer>();
		generateArena();
		round = 0;
	}

	/**
	 * generate enemies
	 */
	private void generate_enemies() {
		for (int i = 0; i < NUM_ENEMIES; i++) {
			Enemy e = new Enemy(i);
			enemies.add(e);
		}
	}

	/**
	 * when it is player's
	 */
	public void playerTurn() {
		if (player.getPlayer_action().getAvailableActions() > 0) {
			if (player.getExperience().addLevel()) {
				System.out.println("Congrats, your level has increased! Now you are stronger. ");
				player.getExperience().increaseLevel();
				player.getPlayerAtt().increaseAtt();
				player.recoverPlayer();
				g.update();
			}
		} else {
			player.getPlayer_action().resetActions();
			enemyTurn();

			play();
		}
	}
	/**
	 * when it is enemies's
	 */
	private void enemyTurn() {

		System.out.println("-- Now it is the enemy turn! --");
		for (var item : enemyposwithID) {
			if (skipenemy.contains(item.getX())) {
				// If the enemy dies, it gets teleported off screen to be later deleted once a
				// new Arena gets generated.
				enemyposwithID.set(item.getX(), new Pair<>(item.getX(), new Pair<Integer, Integer>(99, 99)));
			} else {
				int id = item.getX();
				int actionpt = 0;

				while (actionpt < 2) {
					advance(item, actionpt);
					item = enemyposwithID.get(id);
					actionpt++;
				}
			}
		}

	}

	/**
	 * Checks the Obstacle position for collision events.
	 * 
	 * @param position Param to check.
	 * @return true -> if position is empty; false -> if position has an obstacle.
	 */
	public boolean checkObstaclesPos(Pair<Integer, Integer> position) {
		boolean success = true;
		if (this.obstacles.isEmpty()) {
			return success;
		}
		for (var item : this.obstacles) {
			if (item.getObstaclePos().equals(position)) {
				success = false;
			}
			success = success && true;
		}
		return success;
	}

	/**
	 * Checks the Enemy position for collision events.
	 * 
	 * @param position Param to check.
	 * @return true -> if position is empty; false -> if position has an enemy.
	 */
	public boolean checkEnemyPos(Pair<Integer, Integer> position) {
		if (enemyposwithID.stream().map(e -> e.getY()).anyMatch(p -> p.equals(position))) {
			return false;
		}
		return true;
	}

	/**
	 * Check the player position for collision events.
	 * 
	 * @param position Param to check.
	 * @return true -> if position is empty; false -> if position has a player.
	 */
	public boolean checkPlayerPos(Pair<Integer, Integer> position) {
		if (player.getPlayerPosition().equals(position)) {
			return false;
		}
		return true;
	}

	/**
	 * the enemies move
	 * @param item
	 * @param actionpt
	 */
	private void advance(Pair<Integer, Pair<Integer, Integer>> item, int actionpt) {
		Enemy_move_control.nextMove(item, actionpt);

		// ---- wait in between actions ---
		long end = System.currentTimeMillis() + 300;
		while (end > System.currentTimeMillis()) {
			if (System.currentTimeMillis() > end) {
				g.update();
				break;
			}
		}
		g.update();
	}

	/**
	 * Used to randomize Obstacles position with the help of
	 * {@link #checkEnemyPos(Pair)}, {@link #checkPlayerPos(Pair) and
	 * {{@link #checkObstaclesPos(Pair)}.
	 * 
	 * @param The grid size.
	 * @return An empty position to place the Obstacle.
	 */
	public Pair<Integer, Integer> randPositionObstacles(final int size_X, final int size_Y) {
		Pair<Integer, Integer> pos = new Pair<>(0, 0);
		Random r = new Random();
		boolean success = false;
		while (!success) {

			int x = r.nextInt(size_X);
			int y = r.nextInt(size_X);
			pos = new Pair<>(x, y);
			if (checkObstaclesPos(pos) && checkPlayerPos(pos) && checkEnemyPos(pos)) {
				success = true;
			}
		}
		return pos;
	}

	
	/**
	 * put the player at the ramdomized position at the begining of the game
	 * @param size_X
	 * @param size_Y
	 * @return pair(<x,y) 
	 */
	public Pair<Integer, Integer> rand_pos_player(final int size_X, final int size_Y) {
		Random r = new Random();
		int x = r.nextInt(size_X);
		int y = r.nextInt(size_Y);
		return new Pair<>(x, y);
	}
}
