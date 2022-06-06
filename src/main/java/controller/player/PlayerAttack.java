package controller.player;

import model.player.Pair;

/**
* @author Olivia
*
*/

public interface PlayerAttack {

	/**
	 * attack beetwen the player and the enemie
	 */
	 public void attack(Pair<Integer, Integer> new_heropos2);
	 
	/** @return player's attack_points
	 * 
	 */
	 public int getAttackPoints();
	 
	 public void setAttackPoints(int newAttackPoints);
	 
	 public void resetAttackPoints();
	 public void getHit(int enemyID,int enemyResponseHit);
}
