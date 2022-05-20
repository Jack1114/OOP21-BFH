package controller.entities;

import javafx.util.Pair;

/**
* @author Olivia
*
*/

public interface PlayerAttackController {
	 int getAttackPoints();

	 void Attack(Pair<Integer, Integer> new_heropos2);
	 
	 void GetHit(int enemy_atk, int enemyID);
}
