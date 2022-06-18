package controller.playerAttack;

import model.enemies.Enemy;
import model.player.Pair;

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
	  * Handles Enemy attacks to the Player;
	  * @param enemyID
	  * @param enemyResponseHit
	  */
	 void getHit(int enemyID, int enemyResponseHit);
	 
	 void increaseAtt();
}
