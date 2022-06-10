package controller.player;

import model.enemies.Enemy;
import model.player.Pair;

public interface PlayerAttack {

	/**
	 * attack beetwen the player and the enemie
	 */
	 public void attack(Enemy enemy);
	 
	/** @return player's attack_points
	 * 
	 */
	 public int getAttackPoints();

	 /** 
	 * update player's attack_points
	 */
	 public void setAttackPoints(int newAttackPoints);
	 /** 
	 *the player create a dammage to an anemy
	 */
	 public void getHit(int enemyID,int enemyResponseHit);
}
