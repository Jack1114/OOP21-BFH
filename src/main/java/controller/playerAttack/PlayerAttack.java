package controller.playerAttack;

import model.enemies.Enemy;
import model.player.Pair;

public interface PlayerAttack {

	/**
	 * attack beetwen the player and the enemie
	 */
	 void attack(Enemy enemy);
	 
	/**
	 * 
	 *  @return player's attack_points
	 */
	 int getAttackPoints();

	 /** 
	 * update player's attack_points
	 */
	 void setAttackPoints(int newAttackPoints);
	 
	 /** 
	 *the player get the hit from a enemy and receive damage
	 */
	 void getHit(int enemyID,int enemyResponseHit);
	 void increaseAtt();
}
