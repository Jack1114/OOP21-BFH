package controller.entities;

/**
* @author Olivia
*
*/

public interface PlayerAttackController {
	 int getAttackPoints();

	 void attack(Pair<Integer, Integer> new_heropos2);
	 
	 //void getHit(int enemyResponseHit);
}
