/**
 * 
 */
package controller.player;

import model.player.Pair;


/**
 * @author Olivia
 *
 */
public interface PlayerMovement {

	/**
	 * the player move to the left
	 */
	void left();
	/**
	 * the player move to the right
	 */
	void right();
	/**
	 * the player move to down
	 */
	void down();
	/**
	 * the player move to up
	 */
	void up();
	/**
	 * the player can not move because of an obstacle or something else
	 */
	void stop();
	/**
	 * 
	 * @return true or false
	 * before moving, the player check if there is an enemy and if there is an obstacle 
	 * if there is an obstacle, he check if the obstacle's type is the one that can be cross
	 */	
	boolean check_advancement(Pair<Integer, Integer> player_pos);
}
