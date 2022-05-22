package controller.entities;

/**
 * @author Olivia
 *
 */
public interface PlayerController {
	
	 Pair<Integer,Integer> getPlayerPosition();
	
	 void setPlayerPosition(Pair<Integer,Integer> newPos);
	
	//int getAttackPoints();
	
	//void setAttackPoints(int attackPoints);
	//int getMAXExpPoints(int newExpPoints);

	int getGold();
	
	void gainGold(int gold);
	
	
	

	

	

	
	


	

}
