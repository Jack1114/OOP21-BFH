package controller.player;

import model.enemies.Enemy;
import model.player.Pair;

/**
* @author Olivia
*
*/

public interface PlayerAttack {

	/**
	 * attack beetwen the player and the enemie
	 */
	 public void attack(Enemy enemy);
	 
	/** @return player's attack_points
	 * 
	 */
	 public int getAttackPoints();
	 
	 public void setAttackPoints(int newAttackPoints);
	 
	 public void resetAttackPoints();
	 public void getHit(int enemyID,int enemyResponseHit);
}
