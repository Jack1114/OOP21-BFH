package controller.playerAttack;

import model.enemies.Enemy;

public interface PlayerAttack {
	
	/**
	 * Handles Player attacks to an Enemy.
	 * @param enemy Enemy attacked.
	 */
	 void attack(Enemy enemy);
	 
	/**
	 *  @return player's attackPoints.
	 */
	 int getAttackPoints();
	 
	 /**
	  * @param newAttackPoints.
	  */
	 void setAttackPoints(int newAttackPoints);
	 
	 /**
	  * the power of the enemy attack on the player
	  * @param enemyID
	  * @param enemyResponseHit
	  */
	 void getHit(int enemyID, int enemyResponseHit);
	 
	 /**
	 * add player attack points
	 */
	 void increaseAtt();
}
