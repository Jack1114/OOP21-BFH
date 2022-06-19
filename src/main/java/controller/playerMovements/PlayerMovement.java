/**
 * 
 */
package controller.playerMovements;

import model.player.Pair;


/**
 * @author Olivia
 *
 */
public interface PlayerMovement {

	/**
	 * The Player move to the left.
	 */
	void left();
	
	/**
	 * The Player move to the right.
	 */
	void right();
	
	/**
	 * The Player move to down.
	 */
	
	void down();
	
	/**
	 * The Player move to up.
	 */
	void up();
	
	/**
	 * The Player does not move or cannot move.
	 */
	void stop();
	
	/**
	 * Checks if there is an Enemy or an Obstacle.
	 * If there is an obstacle, checks which type and if it can be crossed.
	 * @return 
	 * 	true or false
	 */	
	boolean check_advancement(Pair<Integer, Integer> player_pos);
}
