package controller.entities;

/**
 * @author Olivia
 *
 */
public interface PlayerController {
	
	int getX();
	
	int getY();
	
	void setX(int newx);
	
	void setY(int newy);
	
	int SetLifePoints(int newlifePoints);
	
	int getLifePoints();
	
	void setMAXLifePoints();
	
	int getMAXLifePoints();
	
	int getLevel();
	
	void checkAddLevel();
	
	//int getAttackPoints();
	
	//void setAttackPoints(int attackPoints);
	
	int getExpPoints();
	
	//int getMAXExpPoints(int newExpPoints);

	int GetGold();
	
	void GainGold(int gold);
	
	void GainExp(int exp);
	
	int getAttackPoints();
	
	

	

	

	
	


	

}
